package com.github.netelli;

import com.github.netelli.model.Product;
import org.apache.log4j.Logger;

import java.util.List;

public class JDBCStuding {
    private final static Logger logger = Logger.getLogger(JDBCStuding.class);

    public static void main(String[] args) throws Exception {
        logger.info("Start working");
        try (ProductsDAO productsDAO = new ProductsDAO("jdbc:h2:mem:test")) {

            productsDAO.init();

            productsDAO.createTables();
            productsDAO.insertData();

            List<Product> products = productsDAO.getProducts();
            products.forEach(logger::info);

            productsDAO.updateData();
            products = productsDAO.getProducts();
            products.forEach(logger::info);

            productsDAO.deleteData();
            products = productsDAO.getProducts();
            products.forEach(logger::info);
        }
        logger.info("Stop working");
    }
}
