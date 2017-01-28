package com.github.netelli.model.config;


/**
 * Simplest approach to stubbing/mocking.
 */
public class DummyConfigParser implements Parser {
    @Override
    public String getJdbcUrl() {
        return "jdbc:h2:mem:test";
    }

}
