package com.github.netelli.model;

import com.google.common.base.Preconditions;
import org.h2.jdbcx.JdbcConnectionPool;

import javax.sql.DataSource;

public class DataSourceWrapperFactory {

    private DataSourceWrapperFactory() {

    }

    public static DataSourceWrapper getWrapper(String jdbcUrl, DataSourceType type) {
        Preconditions.checkNotNull(jdbcUrl);
        Preconditions.checkNotNull(type);

        if (DataSourceType.H2 == type) {
            JdbcConnectionPool connectionPool = JdbcConnectionPool.create(jdbcUrl, "", "");
            return new H2DataSourceWrapper(connectionPool);
        }

        throw new IllegalArgumentException("Unknown type of data source provided=" + type.name());
    }
}
