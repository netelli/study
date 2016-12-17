package com.github.netelli;

import com.github.netelli.model.Product;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductsDAO implements AutoCloseable {

    private final String jdbcUrl;
    private Connection connection;
    final static Logger logger = Logger.getLogger(ProductsDAO.class);

    public ProductsDAO(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public void init() {
        try {
            Class.forName("org.h2.Driver");
            if (connection == null) {
                connection = DriverManager.getConnection(jdbcUrl);
                logger.info("Connection opened");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void deleteData() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            logger.info("Delete items from 'products' with brandId 2");
            statement.execute("delete from products where brandId = 2");
        }
    }

    public List<Product> getProducts() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            logger.info("Get data from 'products'");
            List<Product> products = new ArrayList<>();
            ResultSet rs = statement.executeQuery("select * from products");
            while (rs.next()) {
                Product product = new Product();
                product.setTitle(rs.getString("title"));
                product.setCategoryId(rs.getInt("categoryId"));
                product.setBrandId(rs.getInt("brandId"));
                product.setId(rs.getInt("id"));

                products.add(product);
            }
            products.sort(Comparator.comparing(Product::getId));
            return products;
        }
    }

    public void updateData() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            logger.info("Update brandId for product with id 2");
            statement.execute("update products set brandId = 2 where id = 2");
        }
    }

    public void insertData() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            logger.info("Insert data to tables 'categories', 'brands', 'products'");
            statement.execute("insert into categories(title) values ('Skirts'), ('Pants')");
            statement.execute("insert into brands(title) values ('Versace'), ('Dolce gabbana')");
            statement.execute("insert into products(title, categoryId, brandId) values ('skirt mini', 1, 2), ('skirt midi', 1, 1)," +
                    "('leather skirt', 1, 1)");
        }
    }

    public void createTables() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            logger.info("Create tables: 'categories', 'brands', 'products'");
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

    @Override
    public void close() throws Exception {
        try {
            if (connection != null) {
                connection.close();
                logger.info("Connection closed");
            }
        } catch (Exception e) {
            logger.error("Error while closing connection. " + e.getMessage());
        }
    }
}
