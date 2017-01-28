package com.github.netelli.dao;

import com.github.netelli.dao.jdbc.ProductsDAO;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public interface BaseDAO<T> {

    Logger logger = Logger.getLogger(ProductsDAO.class);

    void createTable() throws SQLException;

    void insertData() throws SQLException;

    List<T> getAll() throws SQLException;
}
