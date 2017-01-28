package com.github.netelli.model;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

public interface DataSourceWrapper extends AutoCloseable {

    default DataSource getDataSource() {
        return null;
    }

    default EntityManager getEntityManger() {
        return null;
    }
}
