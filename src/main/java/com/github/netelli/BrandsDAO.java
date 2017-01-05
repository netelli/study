package com.github.netelli;

import com.github.netelli.model.Brands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nataliiaku on 1/5/2017.
 */
public class BrandsDAO extends TablesDAO {
    public BrandsDAO(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public void createTable() throws SQLException {
        logger.info("Create table: 'brands'");
        try (Statement statement = connection.createStatement()) {

            statement.execute("create table brands(" +
                    "id integer primary key auto_increment, " +
                    "title varchar(100));");
        }
    }

    public void insertData() throws SQLException {
        logger.info("Insert data to table 'brands'");
        try (Statement statement = connection.createStatement()) {
            statement.execute("insert into brands(title) values ('Versace'), ('Dolce gabbana')");
        }
    }

    public List<Brands> getBrands() throws SQLException {
        logger.info("Get data from 'brands'");
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * from brands")) {
            List<Brands> brands = new ArrayList<>();
            while (rs.next()) {
                Brands brand = new Brands();
                brand.setTitle(rs.getString("title"));
                brand.setId(rs.getInt("id"));

                brands.add(brand);
            }
            return brands;
        }
    }
}
