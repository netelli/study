package com.github.netelli;

import java.sql.*;

/**
 * Created by user on 08.12.2016.
 */
public class ProductsDAO implements AutoCloseable {

    private final String jdbcUrl;
    Connection connection = null;

    public void init() {
        try {
            Class.forName("org.h2.Driver");
            if (connection == null) {
                connection = DriverManager.getConnection(jdbcUrl);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ProductsDAO(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public void deleteData() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("delete from products where brandId = 2");
    }

    public ResultSet getProducts() throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery("select * from products");
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

    @Override
    public void close() throws Exception {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            //do nothing
        }
    }
}
