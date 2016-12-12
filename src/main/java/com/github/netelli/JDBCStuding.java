package com.github.netelli;

import java.sql.ResultSet;

/**
 * Created by user on 05.12.2016.
 */
public class JDBCStuding {
    public static void main(String[] args) throws Exception {
        DBDrivers.load();
        ProductsDAO productsDAO = new ProductsDAO("jdbc:h2:mem:test");


        productsDAO.createTables();
        productsDAO.insertData();

        String tableName = "products";
        ResultSet rs = productsDAO.getData(tableName);
        productsDAO.displayData(rs);

        productsDAO.updateData();
        productsDAO.displayData(productsDAO.getData(tableName));

        productsDAO.deleteData();
        productsDAO.displayData(productsDAO.getData(tableName));
    }
}
