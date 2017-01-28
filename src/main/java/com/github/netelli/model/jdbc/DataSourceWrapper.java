package com.github.netelli.model.jdbc;

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
