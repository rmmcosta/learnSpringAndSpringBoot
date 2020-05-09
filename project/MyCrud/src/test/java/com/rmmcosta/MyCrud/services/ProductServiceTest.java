package com.rmmcosta.MyCrud.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductServiceTest {
    @Test
    void listAllProducts() {
        ProductServiceImpl productService = new ProductServiceImpl();
        assertEquals(4, productService.listAllProducts().size());
    }

    @Test
    void deleteProduct() {
        ProductServiceImpl productService = new ProductServiceImpl();
        assertEquals(4, productService.listAllProducts().size());
        productService.deleteProduct(1);
        assertEquals(3, productService.listAllProducts().size());
    }

    @Test
    void deleteAllProduct() {
        ProductService productService = new ProductServiceImpl();
        int size = productService.getNumProducts();
        assertEquals(4, size);
        for (int i = 1; i <= size; i++) {
            productService.deleteProduct(i);
            assertEquals(size-i, productService.getNumProducts());
        }
    }

    @Test
    void getId() {
        ProductService productService = new ProductServiceImpl();
        int size = productService.getNumProducts();
        for (int i = 1; i <= size; i++) {
            int currId = productService.getProduct(i).getId();
            assertEquals(i,currId);
        }
    }
}