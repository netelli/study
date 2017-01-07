package com.github.netelli;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO<T> implements AutoCloseable {
    protected final static Logger logger = Logger.getLogger(ProductsDAO.class);
    protected Connection connection;
    protected String jdbcUrl;

    public void init() {
        try {
            logger.info("DB connection creation");
            Class.forName("org.h2.Driver");
            if (connection == null) {
                connection = DriverManager.getConnection(jdbcUrl);
                logger.info("Connection opened");
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public abstract void createTable() throws SQLException;

    public abstract void insertData() throws SQLException;

    public abstract List<T> getAll() throws SQLException;

    @Override
    public void close() throws Exception {
        try {
            if (connection != null) {
                connection.close();
                logger.info("Connection closed");
            }
        } catch (Exception e) {
            logger.error("Error while closing connection. " + e.getMessage(), e);
        }
    }
}
