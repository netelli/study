package com.github.netelli;

import java.sql.*;

/**
 * Created by user on 05.12.2016.
 */
public class JDBCStuding {
    public static void main(String[] args) throws Exception {
        DBConnection dbConnection = new DBConnection();
        dbConnection.init();
        JDBCStuding jdbcStuding = new JDBCStuding();
        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement()) {
            jdbcStuding.createTables(statement);
            jdbcStuding.insertData(statement);

            String tableName = "products";
            ResultSet rs = getData(statement, tableName);
            displayData(rs);

            jdbcStuding.updateData(statement);
            displayData(getData(statement, tableName));

            jdbcStuding.deleteData(statement);
            displayData(getData(statement, tableName));
        }
    }

    private void deleteData(Statement statement) throws SQLException {
        statement.execute("delete from products where brandId = 2");
    }

    private static ResultSet getData(Statement statement, String tableName) throws SQLException {
        return statement.executeQuery("select * from " + tableName);
    }

    private static void displayData(ResultSet rs) throws SQLException {
        System.out.println(("ID") + " | " + ("TITLE") + " | " + ("CATEGORY") + " | " + ("BRAND"));
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " : " + rs.getString("title") + " : " + rs.getInt("categoryId") + " : " + rs.getInt("brandId"));
        }
        System.out.println("_______________________________");
    }

    private void updateData(Statement statement) throws SQLException {
        statement.execute("update products set brandId = 2 where id = 2");
    }

    private void insertData(Statement statement) throws SQLException {
        statement.execute("insert into categories(title) values ('Skirts'), ('Pants')");
        statement.execute("insert into brands(title) values ('Versace'), ('Dolce gabbana')");
        statement.execute("insert into products(title, categoryId, brandId) values ('skirt mini', 1, 2), ('skirt midi', 1, 1)," +
                "('leather skirt', 1, 1)");
    }

    private void createTables(Statement statement) throws SQLException {
        statement.execute("create table categories(" +
                "id integer primary key auto_increment, " +
                "title varchar(100));");

        statement.execute("create table brands(" +
                "id integer primary key auto_increment, " +
                "title varchar(100));");

        statement.execute("create table products(" +
                "id integer primary key auto_increment, " +
                "title varchar(100), categoryId integer, brandId integer," +
                "foreign key (categoryId) references categories (id)," +
                "foreign key (brandId) references brands (id));");
    }
}
