package com.github.netelli.dao.jpa;

import com.github.netelli.dao.ProductsDAO;
import com.github.netelli.model.pojo.Brand;
import com.github.netelli.model.pojo.Product;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class ProductsDAOByJPA extends ProductsDAO {

    private EntityManager em;

    public ProductsDAOByJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insert(Product product) throws SQLException {
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }

    @Override
    public List<Product> getAll() throws SQLException {
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    @Override
    public void updateBrandId(Product product, Brand brand) {
//        Product product = em.find(Product.class, productId);
        em.getTransaction().begin();
        product.setBrand(brand);
        em.getTransaction().commit();
    }

    @Override
    public void remove(Product product) {
        em.getTransaction().begin();
        em.remove(product);
        em.getTransaction().commit();
    }
}