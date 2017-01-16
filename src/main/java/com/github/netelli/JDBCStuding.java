package com.github.netelli;

import com.github.netelli.dao.BrandsDAO;
import com.github.netelli.dao.CategoriesDAO;
import com.github.netelli.dao.ProductsDAO;
import com.github.netelli.model.*;
import com.google.common.base.Preconditions;
import org.apache.log4j.Logger;

import java.util.List;

public class JDBCStuding {
    private final static Logger logger = Logger.getLogger(JDBCStuding.class);

    public static void main(String[] args) throws Exception {
        logger.info("Start working");
        Preconditions.checkArgument(args.length == 1, "expect data source type as an argument");

        String jdbcUrl = "jdbc:h2:mem:test";

        String dsType = args[0];
        DataSourceType dataSourceType = DataSourceType.valueOf(dsType);


        try (DataSourceWrapper dsWrapper = DataSourceWrapperFactory.getWrapper(jdbcUrl, dataSourceType)) {

            ProductsDAO productsDAO = new ProductsDAO(dsWrapper.getDataSource());
            CategoriesDAO categoriesDAO = new CategoriesDAO(dsWrapper.getDataSource());
            BrandsDAO brandsDAO = new BrandsDAO(dsWrapper.getDataSource());

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
