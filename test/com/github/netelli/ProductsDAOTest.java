package com.github.netelli;

import org.junit.Test;

import java.sql.ResultSet;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by user on 13.12.2016.
 */
public class ProductsDAOTest {

    @Test
    public void testInsertData() throws Exception {
        ProductsDAO productsDAO = new ProductsDAO("jdbc:h2:mem:test");
        productsDAO.getConnection();
        productsDAO.createTables();
        productsDAO.insertData();

        ResultSet rs = productsDAO.getData("products");

        int count = 0;
        ArrayList<String> products = new ArrayList<>();
        while (rs.next()) {
            count++;
            products.add(rs.getString("title"));
        }

        assertTrue(count == 3);
        assertTrue("skirt mini".equals(products.get(0)));

        productsDAO.closeConnection();
    }
}