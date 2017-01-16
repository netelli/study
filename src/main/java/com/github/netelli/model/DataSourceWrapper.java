package com.github.netelli.model;

import javax.sql.DataSource;

public interface DataSourceWrapper extends AutoCloseable {

    DataSource getDataSource();
}
