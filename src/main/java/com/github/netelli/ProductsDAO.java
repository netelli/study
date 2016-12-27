package com.github.netelli;

import com.github.netelli.model.Product;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
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
            logger.info("DB connection creation");
            Class.forName("org.h2.Driver");
            if (connection == null) {
                connection = DriverManager.getConnection(jdbcUrl);
                logger.info("Connection opened");
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public void deleteByBrandId(int brandId) throws SQLException {
        logger.info("Delete items from 'products' with brandId " + brandId);
        try (PreparedStatement statement = connection.prepareStatement("delete from products where brandId = ?")) {
            statement.setInt(1, brandId);
            statement.executeUpdate();
        }
    }

    public List<Product> getProducts() throws SQLException {
        logger.info("Get data from 'products'");
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * from products")) {
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setTitle(rs.getString("title"));
                product.setCategoryId(rs.getInt("categoryId"));
                product.setBrandId(rs.getInt("brandId"));
                product.setId(rs.getInt("id"));

                products.add(product);
            }
            return products;
        }
    }

    public void updateBrandId(int brandId, int productId) throws SQLException {
        logger.info("Update brandId for product with id " + productId);
        try (PreparedStatement statement = connection.prepareStatement("update products set brandId = ? where id = ?")) {
            statement.setInt(1, brandId);
            statement.setInt(2, productId);
            statement.executeUpdate();
        }
    }

    public void insertData() throws SQLException {
        logger.info("Insert data to tables 'categories', 'brands', 'products'");
        try (Statement statement = connection.createStatement()) {
            statement.execute("insert into categories(title) values ('Skirts'), ('Pants')");
            statement.execute("insert into brands(title) values ('Versace'), ('Dolce gabbana')");
            statement.execute("insert into products(title, categoryId, brandId) values ('skirt mini', 1, 2), " +
                    "('skirt midi', 1, 1), ('leather skirt', 1, 1)");
        }
    }

    public void createTables() throws SQLException {
        logger.info("Create tables: 'categories', 'brands', 'products'");
        try (Statement statement = connection.createStatement()) {
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
            logger.error("Error while closing connection. " + e.getMessage(), e);
        }
    }
}
