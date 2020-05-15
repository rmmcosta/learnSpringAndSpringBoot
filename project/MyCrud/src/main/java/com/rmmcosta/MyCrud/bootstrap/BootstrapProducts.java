package com.rmmcosta.MyCrud.bootstrap;

import com.rmmcosta.MyCrud.domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BootstrapProducts {
    public static List<Product> getBootstrapProducts() {
        List<Product> productList = new ArrayList<>();
        Product product;

        product = new Product();
        product.setId(1);
        product.setName("Samsung S10");
        product.setDescription("Smartphone running Android 9.");
        product.setPrice(new BigDecimal("679.99"));
        product.setImageUrl("https://www.worten.pt/i/af6e5cad58f7001b1ebb0e536e45dc12c0d2a76c.jpg");
        product.setCreatedOn(new Date());
        productList.add(product);

        product = new Product();
        product.setId(2);
        product.setName("iPhone XR");
        product.setDescription("Smartphone running iOS 12.");
        product.setPrice(new BigDecimal("739.99"));
        product.setImageUrl("https://www.worten.pt/i/66a71615da0f9ca5c10be0d34843e7ccfcb1cef9.jpg");
        product.setCreatedOn(new Date());
        productList.add(product);

        product = new Product();
        product.setId(3);
        product.setName("Samsung S20");
        product.setDescription("Smartphone running Android 10.");
        product.setPrice(new BigDecimal("929.99"));
        product.setImageUrl("https://www.worten.pt/i/c753e9bac4f1a6d0cbe8a2be63134768f1c29e0a.jpg");
        product.setCreatedOn(new Date());
        productList.add(product);

        product = new Product();
        product.setId(4);
        product.setName("iPhone 11");
        product.setDescription("Smartphone running iOS 13.");
        product.setPrice(new BigDecimal("829.99"));
        product.setImageUrl("https://www.worten.pt/i/ce3be0c610c24f2929c82cc6c04f2aa5ddb90189.jpg");
        product.setCreatedOn(new Date());
        productList.add(product);

        return productList;
    }
}
