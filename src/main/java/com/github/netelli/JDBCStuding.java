package com.github.netelli;

import java.sql.*;

/**
 * Created by user on 05.12.2016.
 */
public class JDBCStuding {
    public static void main(String[] args) throws Exception {
        DBConnection dbConnectio = new DBConnection();
        ProductsDAO productsDAO = new ProductsDAO();

        try (Connection connection = dbConnectio.getConnection();
             Statement statement = connection.createStatement()) {
            productsDAO.createTables(statement);
            productsDAO.insertData(statement);

            String tableName = "products";
            ResultSet rs = productsDAO.getData(statement, tableName);
            productsDAO.displayData(rs);

            productsDAO.updateData(statement);
            productsDAO.displayData(productsDAO.getData(statement, tableName));

            productsDAO.deleteData(statement);
            productsDAO.displayData(productsDAO.getData(statement, tableName));
        }
    }
}
