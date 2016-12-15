package com.github.netelli;

import com.github.netelli.model.Product;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ProductsDAOTest {

    @Test
    public void testInsertData() throws Exception {
        ProductsDAO productsDAO = new ProductsDAO("jdbc:h2:mem:test");
        productsDAO.init();
        productsDAO.createTables();
        productsDAO.insertData();

        List<Product> products = productsDAO.getProducts();

        assertTrue(products.size() == 3);
//        assertEquals("skirt mini", products.get(0));
        productsDAO.close();

    }

    @Test(expected = RuntimeException.class)
    public void testInit_WithError() throws Exception {
        try (ProductsDAO productsDAO = new ProductsDAO("execute exception")) {
            productsDAO.init();
        }
    }
}