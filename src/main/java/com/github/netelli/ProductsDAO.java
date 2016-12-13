package com.github.netelli;

import java.sql.*;

/**
 * Created by user on 08.12.2016.
 */
public class ProductsDAO {

    private final String jdbcUrl;
    Connection connection = null;

    public Connection getConnection() {
        try {
            Class.forName("org.h2.Driver");
            if (connection == null) {
                connection = DriverManager.getConnection(jdbcUrl);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            //do nothing
        }
    }

    public ProductsDAO(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public void deleteData() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("delete from products where brandId = 2");
    }

    public ResultSet getData(String tableName) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery("select * from " + tableName);
    }

    public void displayData(ResultSet rs) throws SQLException {
        System.out.println(("ID") + " | " + ("TITLE") + " | " + ("CATEGORY") + " | " + ("BRAND"));
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " : " + rs.getString("title") + " : " + rs.getInt("categoryId") + " : " + rs.getInt("brandId"));
        }
        System.out.println("_______________________________");
    }

    public void updateData() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("update products set brandId = 2 where id = 2");
    }

    public void insertData() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("insert into categories(title) values ('Skirts'), ('Pants')");
        statement.execute("insert into brands(title) values ('Versace'), ('Dolce gabbana')");
        statement.execute("insert into products(title, categoryId, brandId) values ('skirt mini', 1, 2), ('skirt midi', 1, 1)," +
                "('leather skirt', 1, 1)");
    }

    public void createTables() throws SQLException {
        Statement statement = connection.createStatement();
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
