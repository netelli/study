package com.github.netelli.dao.jpa;

import com.github.netelli.dao.BrandsDAO;
import com.github.netelli.model.pojo.Brand;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class BrandsDAOByJPA extends BrandsDAO {

    private EntityManager em;

    public BrandsDAOByJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insert(Brand brand) throws SQLException {
        em.getTransaction().begin();
        em.persist(brand);
        em.getTransaction().commit();
    }

    @Override
    public List<Brand> getAll() throws SQLException {
        return em.createQuery("SELECT b FROM Brand b", Brand.class).getResultList();
    }
}