package com.github.netelli;

import java.sql.*;

/**
 * Created by user on 05.12.2016.
 */
public class JDBCStuding {
    public static void main(String[] args) throws Exception {
        init();
        try (Connection connection = getConnection();
            Statement statement = connection.createStatement()) {
            statement.execute("create table categories(" +
                    "id integer primary key auto_increment, " +
                    "title varchar(100));");
            statement.execute("insert into categories(title) values ('skirts'), ('pants')");
            ResultSet rs = statement.executeQuery("select * from categories");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " : " + rs.getString("title"));
            }
        }
    }

    public static void init() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:mem:test");
    }
}
