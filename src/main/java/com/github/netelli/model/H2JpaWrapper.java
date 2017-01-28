package com.github.netelli.model;

import org.apache.log4j.Logger;

import javax.persistence.EntityManager;

public class H2JpaWrapper implements DataSourceWrapper {

    private EntityManager em;
    private final static Logger logger = Logger.getLogger(H2JpaWrapper.class);

    public H2JpaWrapper(EntityManager em) {
        this.em = em;
    }

    @Override
    public EntityManager getEntityManger() {
        return em;
    }

    @Override
    public void close() throws Exception {
        logger.info(">>> close entity manager");
        em.close();

        DataSourceWrapperFactory.emf.close();
    }
}
