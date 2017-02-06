package com.github.netelli;

import com.github.netelli.dao.BrandsDAO;
import com.github.netelli.dao.ProductsDAO;
import com.github.netelli.dao.jpa.BrandsDAOByJPA;
import com.github.netelli.dao.jpa.ProductsDAOByJPA;
import com.github.netelli.model.config.ConfigParser;
import com.github.netelli.model.config.Parser;
import com.github.netelli.model.pojo.Brand;
import com.github.netelli.model.pojo.Product;
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

            ProductsDAO productsDAO = new ProductsDAOByJPA(em);
//            CategoriesDAO categoriesDAO = new CategoriesDAO(dsWrapper.getDataSource());
            BrandsDAO brandsDAO = new BrandsDAOByJPA(em);

            Brand brand = new Brand();
            brand.setTitle("Zhytomyrski shkarpetky");
            brandsDAO.insert(brand);

            Product product = new Product();
            product.setTitle("Dress");
            product.setBrand(brand);
            productsDAO.insert(product);

//            List<Category> categories = categoriesDAO.getAll();
//            categories.forEach(logger::info);

            logger.info(">>> brands:");
            List<Brand> brands = brandsDAO.getAll();
            brands.forEach(logger::info);

            logger.info(">>> products:");
            List<Product> products = productsDAO.getAll();
            products.forEach(logger::info);
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
