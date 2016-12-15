package com.github.netelli;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by user on 05.12.2016.
 */
public class JDBCStuding {
    public static void main(String[] args) throws Exception {
        try (ProductsDAO productsDAO = new ProductsDAO("jdbc:h2:mem:test")) {

            productsDAO.init();

            productsDAO.createTables();
            productsDAO.insertData();

            ResultSet rs = productsDAO.getProducts();
            displayData(rs);

            productsDAO.updateData();
            displayData(productsDAO.getProducts());

            productsDAO.deleteData();
            displayData(productsDAO.getProducts());
        }
    }

    private static void displayData(ResultSet rs) throws SQLException {
        System.out.println(("ID") + " | " + ("TITLE") + " | " + ("CATEGORY") + " | " + ("BRAND"));
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " : " + rs.getString("title") + " : " + rs.getInt("categoryId") + " : " + rs.getInt("brandId"));
        }
        System.out.println("_______________________________");
    }
}
