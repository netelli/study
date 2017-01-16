package com.github.netelli.dao;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO<T> {

    protected final static Logger logger = Logger.getLogger(ProductsDAO.class);

    public abstract void createTable() throws SQLException;

    public abstract void insertData() throws SQLException;

    public abstract List<T> getAll() throws SQLException;
}
