package com.github.netelli.dao;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public interface BaseDAO<T> {

    Logger logger = Logger.getLogger(BaseDAO.class);

    void insert(T type) throws SQLException;

    List<T> getAll() throws SQLException;
}
