package com.github.netelli;

import com.github.netelli.dao.BrandsDAO;
import com.github.netelli.dao.jpa.BrandsDAOByJPA;
import com.github.netelli.model.config.ConfigParser;
import com.github.netelli.model.config.Parser;
import com.github.netelli.model.pojo.Brand;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class JDBCStuding {
    private final static Logger logger = Logger.getLogger(JDBCStuding.class);

    public static void main(String[] args) throws Exception {
        logger.info("Start working");

        Parser configParser = ConfigParser.getConfigParser();

        // Use persistence.xml configuration
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manager1");
        EntityManager em = emf.createEntityManager(); // Retrieve an application managed entity manager
        try {

//            ProductsDAO productsDAO = new ProductsDAO(dsWrapper.getDataSource());
//            CategoriesDAO categoriesDAO = new CategoriesDAO(dsWrapper.getDataSource());
            BrandsDAO brandsDAO = new BrandsDAOByJPA(em);

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
        } finally {
            em.close();
            emf.close();
        }
        logger.info("Stop working");
    }
}
