package com.github.netelli.model.config;

import com.github.netelli.model.jdbc.DataSourceType;
import com.google.common.base.Preconditions;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

public class ConfigParser implements Parser {

    private static ConfigParser instance;
    private String jdbcUrl;
    private DataSourceType dsType;

    private ConfigParser() {
    }

    public static ConfigParser getConfigParser() throws ConfigurationException {
        if (instance == null) {
            instance = new ConfigParser();
            instance.parseConfig();
        }
        return instance;
    }

    private void parseConfig() throws ConfigurationException {
        Configurations configs = new Configurations();
        Configuration config = configs.properties(new File("config.properties"));

        String jdbcUrl = config.getString("jdbc.url");
        Preconditions.checkNotNull(jdbcUrl);
        this.jdbcUrl = jdbcUrl;

        String dsType = config.getString("datasource.type");
        Preconditions.checkNotNull(dsType);
        this.dsType = DataSourceType.valueOf(dsType.toUpperCase());
    }

    @Override
    public String getJdbcUrl() {
        return jdbcUrl;
    }

    @Override
    public DataSourceType getDsType() {
        return dsType;
    }
}
