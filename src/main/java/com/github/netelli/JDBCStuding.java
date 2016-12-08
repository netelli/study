package com.github.netelli;

import java.sql.*;

/**
 * Created by user on 05.12.2016.
 */
public class JDBCStuding {
    public static void main(String[] args) throws Exception {
        DBConnection dbConnection = new DBConnection();
        dbConnection.init();
        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement()) {
            createTables(statement);
            insertData(statement);

            ResultSet rs = statement.executeQuery("select * from products");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " : " + rs.getString("title") + " : " + rs.getInt("categoryId"));
            }
        }
    }

    private static void insertData(Statement statement) throws SQLException {
        statement.execute("insert into categories(title) values ('Skirts'), ('Pants')");
        statement.execute("insert into brands(title) values ('Versace'), ('Dolce gabbana')");
        statement.execute("insert into products(title, categoryId, brandId) values ('skirt mini', 1, 2), ('skirt midi', 1, 1)");
    }

    private static void createTables(Statement statement) throws SQLException {
        statement.execute("create table categories(" +
                "id integer primary key auto_increment, " +
                "title varchar(100));");

        statement.execute("create table brands(" +
                "id integer primary key auto_increment, " +
                "title varchar(100));");

        statement.execute("create table products(" +
                "id integer primary key auto_increment, " +
                "title varchar(100), categoryId integer, brandId integer" +
                "constraint categoryId foreign key (id) references categories (id)" +
                "constraint brandId foreign key (id) references brands (id));");
    }
}
