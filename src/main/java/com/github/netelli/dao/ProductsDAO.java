package com.github.netelli.dao;

import com.github.netelli.model.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsDAO extends BaseDAO<Product> {
    private DataSource dataSource;

    public ProductsDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void deleteByBrandId(int brandId) throws SQLException {
        logger.info("Delete items from 'products' with brandId " + brandId);
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("delete from products where brandId = ?")) {
            statement.setInt(1, brandId);
            statement.executeUpdate();
        }
    }

    @Override
    public List<Product> getAll() throws SQLException {
        logger.info("Get data from 'products'");
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
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
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("update products set brandId = ? where id = ?")) {
            statement.setInt(1, brandId);
            statement.setInt(2, productId);
            statement.executeUpdate();
        }
    }

    @Override
    public void insertData() throws SQLException {
        logger.info("Insert data to table 'products'");
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("insert into products(title, categoryId, brandId) values ('skirt mini', 1, 2), " +
                    "('skirt midi', 1, 1), ('leather skirt', 1, 1)");
        }
    }

    @Override
    public void createTable() throws SQLException {
        logger.info("Create table: 'products'");
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute("create table if not exists products(" +
                    "id integer primary key auto_increment, " +
                    "title varchar(100), categoryId integer, brandId integer," +
                    "foreign key (categoryId) references categories (id)," +
                    "foreign key (brandId) references brands (id));");
        }
    }
}
