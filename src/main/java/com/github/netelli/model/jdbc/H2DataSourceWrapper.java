package com.github.netelli.model.jdbc;

import org.h2.jdbcx.JdbcConnectionPool;

import javax.sql.DataSource;

public class H2DataSourceWrapper implements DataSourceWrapper {

    private JdbcConnectionPool jdbcConnectionPool;

    public H2DataSourceWrapper(JdbcConnectionPool jdbcConnectionPool) {
        this.jdbcConnectionPool = jdbcConnectionPool;
    }

    @Override
    public DataSource getDataSource() {
        return jdbcConnectionPool;
    }

    @Override
    public void close() {
        jdbcConnectionPool.dispose();
    }
}
