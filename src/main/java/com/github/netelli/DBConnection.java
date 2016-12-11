package com.github.netelli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by user on 08.12.2016.
 */
public class DBConnection {
    public DBConnection() throws ClassNotFoundException {
        init();
    }

    private void init() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:mem:test");
    }
}
