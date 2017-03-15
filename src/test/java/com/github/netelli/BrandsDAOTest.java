package com.github.netelli;

import com.github.netelli.dao.BrandsDAO;
import com.github.netelli.dao.jpa.BrandsDAOByJPA;
import com.github.netelli.model.pojo.Brand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BrandsDAOTest {

    private BrandsDAO brandsDAO;
    private  EntityManagerFactory emf;
    private EntityManager em;

    @Before
    public void setUp() throws Exception {
        emf = Persistence.createEntityManagerFactory("manager1");
        em = emf.createEntityManager(); // Retrieve an application managed entity manager
        brandsDAO = new BrandsDAOByJPA(em);
    }

    @After
    public void tearDown() throws Exception {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    @Test
    public void testInsertData() throws Exception {
        Brand brand = new Brand();
        String title = "Zhytomyrski shkarpetky";
        brand.setTitle(title);
        brandsDAO.insert(brand);

        List<Brand> brands = brandsDAO.getAll();

        assertEquals(1, brands.size());
        assertEquals(title, brands.get(0).getTitle());
    }
}