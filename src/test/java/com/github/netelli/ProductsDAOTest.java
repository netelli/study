package com.github.netelli;

import com.github.netelli.dao.BrandsDAO;
import com.github.netelli.dao.CategoriesDAO;
import com.github.netelli.dao.ProductsDAO;
import com.github.netelli.model.DataSourceWrapper;
import com.github.netelli.model.DataSourceWrapperFactory;
import com.github.netelli.model.DataSourceType;
import com.github.netelli.model.Product;
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
    private BrandsDAO brandsDAO;
    private DataSourceWrapper dataSourceWrapper;

    @Before
    public void setUp() throws Exception {
        String jdbcUrl = "jdbc:h2:mem:test";
        dataSourceWrapper = DataSourceWrapperFactory.getWrapper(jdbcUrl, DataSourceType.H2);

        categoriesDAO = new CategoriesDAO(dataSourceWrapper.getDataSource());
        categoriesDAO.createTable();

        brandsDAO = new BrandsDAO(dataSourceWrapper.getDataSource());
        brandsDAO.createTable();

        productsDAO = new ProductsDAO(dataSourceWrapper.getDataSource());
        productsDAO.createTable();
    }

    @After
    public void tearDown() throws Exception {
        categoriesDAO.close();
        brandsDAO.close();
        productsDAO.close();

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