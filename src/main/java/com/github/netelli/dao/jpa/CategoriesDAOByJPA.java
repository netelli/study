package com.github.netelli.dao.jpa;

import com.github.netelli.dao.CategoriesDAO;
import com.github.netelli.model.pojo.Category;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class CategoriesDAOByJPA extends CategoriesDAO {

    private EntityManager em;

    public CategoriesDAOByJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public void insert(Category category) throws SQLException {
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
    }

    @Override
    public List<Category> getAll() throws SQLException {
        return em.createQuery("SELECT b FROM Category b", Category.class).getResultList();
    }

    @Override
    public List<Category> getById(int id) {
        return null;
    }
}