package com.github.netelli;

import com.github.netelli.dao.jdbc.BrandsDAOByJDBC;
import com.github.netelli.dao.jdbc.CategoriesDAO;
import com.github.netelli.dao.jdbc.ProductsDAO;
import com.github.netelli.model.jdbc.DataSourceWrapper;
import com.github.netelli.model.jdbc.H2DataSourceWrapper;
import com.github.netelli.model.pojo.Product;
import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProductsDAOTest {

    private ProductsDAO productsDAO;
    private CategoriesDAO categoriesDAO;
    private BrandsDAOByJDBC brandsDAO;
    private DataSourceWrapper dataSourceWrapper;

    @Before
    public void setUp() throws Exception {
        JdbcConnectionPool connectionPool = JdbcConnectionPool.create("jdbc:h2:mem:test", "", "");
        dataSourceWrapper = new H2DataSourceWrapper(connectionPool);

        categoriesDAO = new CategoriesDAO(dataSourceWrapper.getDataSource());
        categoriesDAO.createTable();

        brandsDAO = new BrandsDAOByJDBC(dataSourceWrapper.getDataSource());
        brandsDAO.createTable();

        productsDAO = new ProductsDAO(dataSourceWrapper.getDataSource());
        productsDAO.createTable();
    }

    @After
    public void tearDown() throws Exception {
        dataSourceWrapper.close();
    }

    @Test
    public void testInsertData() throws Exception {
        categoriesDAO.insertData();
        brandsDAO.insertData();
        productsDAO.insertData();

        List<Product> products = productsDAO.getAll();
        products.sort(Comparator.comparing(Product::getId));

        assertEquals(3, products.size());
        assertNotNull(products.get(0));
        assertEquals("skirt mini", products.get(0).getTitle());

        assertNotNull(products.get(1));
        assertEquals(1, products.get(1).getBrandId());
    }

    @Test
    public void testUpdateData() throws Exception {
        categoriesDAO.insertData();
        brandsDAO.insertData();
        productsDAO.insertData();
        productsDAO.updateBrandId(2, 2);

        List<Product> products = productsDAO.getAll();
        products.sort(Comparator.comparing(Product::getId));

        assertNotNull(products.get(1));
        assertEquals(2, products.get(1).getBrandId());
    }

    @Test
    public void testDeleteData() throws Exception {
        categoriesDAO.insertData();
        brandsDAO.insertData();
        productsDAO.insertData();
        productsDAO.updateBrandId(2, 2);
        productsDAO.deleteByBrandId(2);

        List<Product> products = productsDAO.getAll();
        assertEquals(1, products.size());
    }
}