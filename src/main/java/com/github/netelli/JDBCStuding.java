package com.github.netelli;

import com.github.netelli.dao.BaseDAO;
import com.github.netelli.dao.DaoFactory;
import com.github.netelli.dao.PersistenceType;
import com.github.netelli.dao.jdbc.CategoriesDAO;
import com.github.netelli.dao.jdbc.ProductsDAO;
import com.github.netelli.model.*;
import com.github.netelli.model.config.ConfigParser;
import com.github.netelli.model.config.Parser;
import com.github.netelli.model.pojo.Brand;
import com.github.netelli.model.pojo.Category;
import com.github.netelli.model.pojo.Product;
import org.apache.log4j.Logger;

import java.util.List;

public class JDBCStuding {
    private final static Logger logger = Logger.getLogger(JDBCStuding.class);

    public static void main(String[] args) throws Exception {
        logger.info("Start working");

        Parser configParser = ConfigParser.getConfigParser();
        PersistenceType persistenceType = PersistenceType.JPA;

        DataSourceWrapper dsWrapper = DataSourceWrapperFactory.getWrapper(configParser, persistenceType);
        try (DaoFactory daoFactory = new DaoFactory(dsWrapper, persistenceType)) {

            BaseDAO brandsDAO = daoFactory.getBrandsDao();
//            ProductsDAO productsDAO = new ProductsDAO(dsWrapper.getDataSource());
//            CategoriesDAO categoriesDAO = new CategoriesDAO(dsWrapper.getDataSource());
//            BrandsDAOByJDBC brandsDAO = new BrandsDAOByJDBC(dsWrapper.getDataSource());

//            categoriesDAO.createTable();
            brandsDAO.createTable();
//            productsDAO.createTable();

//            categoriesDAO.insertData();
            brandsDAO.insertData();
//            productsDAO.insertData();

//            List<Category> categories = categoriesDAO.getAll();
//            categories.forEach(logger::info);

            List<Brand> brands = brandsDAO.getAll();
            brands.forEach(logger::info);

//            List<Product> products = productsDAO.getAll();
//            products.forEach(logger::info);
//
//            productsDAO.updateBrandId(2, 2);
//            products = productsDAO.getAll();
//            products.forEach(logger::info);
//
//            productsDAO.deleteByBrandId(2);
//            products = productsDAO.getAll();
//            products.forEach(logger::info);
        }
        logger.info("Stop working");
    }
}
