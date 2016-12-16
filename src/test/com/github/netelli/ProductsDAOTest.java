package com.github.netelli;

import com.github.netelli.model.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductsDAOTest {

    private ProductsDAO productsDAO;

    @Before
    public void setUp() throws Exception {
        productsDAO = new ProductsDAO("jdbc:h2:mem:test");
    }

    @After
    public void tearDown() throws Exception {
        productsDAO.close();
    }

    @Test
    public void testInsertData() throws Exception {
        productsDAO.init();
        productsDAO.createTables();
        productsDAO.insertData();

        List<Product> products = productsDAO.getProducts();

        assertEquals(3, products.size());
        //TODO fix test
//        assertEquals("skirt mini", products.get(0));
    }

    @Test(expected = RuntimeException.class)
    public void testInit_WithError() throws Exception {
        try (ProductsDAO productsDAO = new ProductsDAO("execute exception")) {
            productsDAO.init();
        }
    }
}