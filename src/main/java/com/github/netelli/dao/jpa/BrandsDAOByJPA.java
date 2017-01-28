package com.github.netelli.dao.jpa;

import com.github.netelli.dao.BaseDAO;
import com.github.netelli.model.pojo.Brand;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.List;

public class BrandsDAOByJPA implements BaseDAO<Brand> {

    private EntityManager em;

    public BrandsDAOByJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public void createTable() throws SQLException {

    }

    @Override
    public void insertData() throws SQLException {

    }

    @Override
    public List<Brand> getAll() throws SQLException {
        return em.createQuery("SELECT b FROM Brand b", Brand.class).getResultList();
    }
}