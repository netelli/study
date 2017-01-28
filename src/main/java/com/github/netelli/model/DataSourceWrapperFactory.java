package com.github.netelli.model;

import com.github.netelli.dao.PersistenceType;
import com.github.netelli.model.config.Parser;
import com.google.common.base.Preconditions;
import org.h2.jdbcx.JdbcConnectionPool;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataSourceWrapperFactory {

    public static EntityManagerFactory emf;

    private DataSourceWrapperFactory() {

    }

    public static DataSourceWrapper getWrapper(Parser configParser, PersistenceType persistenceType) {
        DataSourceType type = configParser.getDsType();
        Preconditions.checkNotNull(type);

        if (persistenceType == PersistenceType.JDBC) {
            String jdbcUrl = configParser.getJdbcUrl();
            Preconditions.checkNotNull(jdbcUrl);

            if (DataSourceType.H2 == type) {
                JdbcConnectionPool connectionPool = JdbcConnectionPool.create(jdbcUrl, "", "");
                return new H2DataSourceWrapper(connectionPool);
            }
        }

        if (persistenceType == PersistenceType.JPA) {

            // Use persistence.xml configuration
            emf = Persistence.createEntityManagerFactory("manager1");
            EntityManager em = emf.createEntityManager(); // Retrieve an application managed entity manager
            if (DataSourceType.H2 == type) {
                return new H2JpaWrapper(em);
            }
        }
        throw new IllegalArgumentException("Unknown type of data source provided=" + type.name());
    }
}
