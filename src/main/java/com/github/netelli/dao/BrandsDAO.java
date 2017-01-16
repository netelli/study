package com.github.netelli.dao;

import com.github.netelli.model.Brand;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nataliiaku on 1/5/2017.
 */
public class BrandsDAO extends BaseDAO<Brand> {

    private DataSource dataSource;

    public BrandsDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createTable() throws SQLException {
        logger.info("Create table: 'brands'");
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute("create table if not exists brands(" +
                    "id integer primary key auto_increment, " +
                    "title varchar(100));");
        }
    }

    @Override
    public void insertData() throws SQLException {
        logger.info("Insert data to table 'brands'");
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute("insert into brands(title) values ('Versace'), ('Dolce gabbana')");
        }
    }

    @Override
    public List<Brand> getAll() throws SQLException {
        logger.info("Get data from 'brands'");
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * from brands")) {
            List<Brand> brands = new ArrayList<>();
            while (rs.next()) {
                Brand brand = new Brand();
                brand.setTitle(rs.getString("title"));
                brand.setId(rs.getInt("id"));

                brands.add(brand);
            }
            return brands;
        }
    }
}
