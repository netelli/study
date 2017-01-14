package com.github.netelli.dao;

import com.github.netelli.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nataliiaku on 1/5/2017.
 */
public class CategoriesDAO extends BaseDAO<Category> {
    public CategoriesDAO(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    @Override
    public void createTable() throws SQLException {
        logger.info("Create table: 'categories'");
        try (Statement statement = connection.createStatement()) {
            statement.execute("create table if not exists categories(" +
                    "id integer primary key auto_increment, " +
                    "title varchar(100));");
        }
    }

    @Override
    public void insertData() throws SQLException {
        logger.info("Insert data to table 'categories'");
        try (Statement statement = connection.createStatement()) {
            statement.execute("insert into categories(title) values ('Skirts'), ('Pants')");
        }
    }

    @Override
    public List<Category> getAll() throws SQLException {
        logger.info("Get data from 'categories'");
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * from categories")) {
            List<Category> categories = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category();
                category.setTitle(rs.getString("title"));
                category.setId(rs.getInt("id"));

                categories.add(category);
            }
            return categories;
        }
    }
}
