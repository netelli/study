package com.github.netelli.model.config;

import com.github.netelli.model.jdbc.DataSourceType;

public interface Parser {
    String getJdbcUrl();

    DataSourceType getDsType();
}
