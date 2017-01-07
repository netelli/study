package com.github.netelli;

import com.github.netelli.model.Brand;
import com.github.netelli.model.Category;
import com.github.netelli.model.Product;
import org.apache.log4j.Logger;

import java.util.List;

public class JDBCStuding {
    private final static Logger logger = Logger.getLogger(JDBCStuding.class);

    public static void main(String[] args) throws Exception {
        logger.info("Start working");
        String jdbcUrl = "jdbc:h2:mem:test";
        try (ProductsDAO productsDAO = new ProductsDAO(jdbcUrl);
             CategoriesDAO categoriesDAO = new CategoriesDAO(jdbcUrl);
             BrandsDAO brandsDAO = new BrandsDAO(jdbcUrl)) {

            categoriesDAO.init();
            brandsDAO.init();
            productsDAO.init();

            categoriesDAO.createTable();
            brandsDAO.createTable();
            productsDAO.createTable();

            categoriesDAO.insertData();
            brandsDAO.insertData();
            productsDAO.insertData();

            List<Category> categories = categoriesDAO.getAll();
            categories.forEach(logger::info);

            List<Brand> brands = brandsDAO.getAll();
            brands.forEach(logger::info);

            List<Product> products = productsDAO.getAll();
            products.forEach(logger::info);

            productsDAO.updateBrandId(2, 2);
            products = productsDAO.getAll();
            products.forEach(logger::info);

            productsDAO.deleteByBrandId(2);
            products = productsDAO.getAll();
            products.forEach(logger::info);
        }
        logger.info("Stop working");
    }
}
