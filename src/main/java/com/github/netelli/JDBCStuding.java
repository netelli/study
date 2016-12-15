package com.github.netelli;

import com.github.netelli.model.Product;

import java.util.List;

public class JDBCStuding {
    public static void main(String[] args) throws Exception {
        try (ProductsDAO productsDAO = new ProductsDAO("jdbc:h2:mem:test")) {

            productsDAO.init();

            productsDAO.createTables();
            productsDAO.insertData();

            List<Product> products = productsDAO.getProducts();
            products.forEach(System.out::println);


            productsDAO.updateData();
            products.forEach(System.out::println);

            productsDAO.deleteData();
            products.forEach(System.out::println);
        }
    }
}
