package com.rmmcosta.MyCrud.services;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductServiceTest {
    @Test
    void listAllProducts() {
        ProductServiceImpl productService = new ProductServiceImpl();
        assertEquals(4, productService.listAllObjects().size());
    }

    @Test
    void deleteProduct() throws DomainObjectNotFound {
        ProductServiceImpl productService = new ProductServiceImpl();
        assertEquals(4, productService.listAllObjects().size());
        productService.deleteObject(1);
        assertEquals(3, productService.listAllObjects().size());
    }

    @Test
    void deleteAllProduct() throws DomainObjectNotFound {
        AbstractService productService = new ProductServiceImpl();
        int size = productService.getNumObjects();
        assertEquals(4, size);
        for (int i = 1; i <= size; i++) {
            productService.deleteObject(i);
            assertEquals(size-i, productService.getNumObjects());
        }
    }

    @Test
    void getId() throws DomainObjectNotFound {
        AbstractService productService = new ProductServiceImpl();
        int size = productService.getNumObjects();
        for (int i = 1; i <= size; i++) {
            int currId = productService.getObjectById(i).getId();
            assertEquals(i,currId);
        }
    }
}