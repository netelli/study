package com.github.netelli.model.config;

import com.github.netelli.model.jdbc.DataSourceType;

/**
 * Simplest approach to stubbing/mocking.
 */
public class DummyConfigParser implements Parser {
    @Override
    public String getJdbcUrl() {
        return "jdbc:h2:mem:test";
    }

    @Override
    public DataSourceType getDsType() {
        return DataSourceType.H2;
    }

}
