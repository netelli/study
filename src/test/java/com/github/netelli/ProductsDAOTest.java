package com.github.netelli;

import com.github.netelli.dao.PersistenceType;
import com.github.netelli.dao.jdbc.BrandsDAOByJDBC;
import com.github.netelli.dao.jdbc.CategoriesDAO;
import com.github.netelli.dao.jdbc.ProductsDAO;
import com.github.netelli.model.DataSourceType;
import com.github.netelli.model.DataSourceWrapper;
import com.github.netelli.model.DataSourceWrapperFactory;
import com.github.netelli.model.pojo.Product;
import com.github.netelli.model.config.Parser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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
        //left here as example of simplest approach to stubbing/mocking
        //Parser config = new DummyConfigParser();
        Parser config = Mockito.mock(Parser.class);
        Mockito.when(config.getJdbcUrl()).thenReturn("jdbc:h2:mem:test");
        Mockito.when(config.getDsType()).thenReturn(DataSourceType.H2);
        dataSourceWrapper = DataSourceWrapperFactory.getWrapper(config, PersistenceType.JDBC);

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