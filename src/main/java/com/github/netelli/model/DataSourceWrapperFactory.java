package com.github.netelli.model;

import com.github.netelli.model.config.ConfigParser;
import com.github.netelli.model.config.Parser;
import com.google.common.base.Preconditions;
import org.h2.jdbcx.JdbcConnectionPool;

import javax.sql.DataSource;

public class DataSourceWrapperFactory {

    private DataSourceWrapperFactory() {

    }

    public static DataSourceWrapper getWrapper(Parser configParser) {
        String jdbcUrl = configParser.getJdbcUrl();
        DataSourceType type = configParser.getDsType();

        Preconditions.checkNotNull(jdbcUrl);
        Preconditions.checkNotNull(type);

        if (DataSourceType.H2 == type) {
            JdbcConnectionPool connectionPool = JdbcConnectionPool.create(jdbcUrl, "", "");
            return new H2DataSourceWrapper(connectionPool);
        }

        throw new IllegalArgumentException("Unknown type of data source provided=" + type.name());
    }
}
