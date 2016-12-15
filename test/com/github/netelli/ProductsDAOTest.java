package com.github.netelli;

import org.junit.Test;

import java.sql.ResultSet;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ProductsDAOTest {

    @Test
    public void testInsertData() throws Exception {
        ProductsDAO productsDAO = new ProductsDAO("jdbc:h2:mem:test");
        productsDAO.init();
        productsDAO.createTables();
        productsDAO.insertData();

        ResultSet rs = productsDAO.getProducts();

        int count = 0;
        ArrayList<String> products = new ArrayList<>();
        while (rs.next()) {
            count++;
            products.add(rs.getString("title"));
        }

        assertTrue(count == 3);
        assertTrue("skirt mini".equals(products.get(0)));
        productsDAO.close();

    }

    @Test(expected = RuntimeException.class)
    public void testInit_WithError() throws Exception {
        try (ProductsDAO productsDAO = new ProductsDAO("execute exception")) {
            productsDAO.init();
        }
    }
}