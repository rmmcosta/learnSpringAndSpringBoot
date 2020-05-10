package com.rmmcosta.MyCrud.services;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Customer;
import com.rmmcosta.MyCrud.domain.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

    @Test
    void createOrUpdateProduct() {
        ProductService productService = new ProductServiceImpl();
        int size = productService.listAllObjects().size();
        Product product = new Product();
        String name = "Produto 100", description = "the description", imageUrl = "the image";
        BigDecimal price = new BigDecimal("4.50");
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);
        try {
            Product createProduct = productService.createOrUpdateObject(product);
            product.setId(createProduct.getId());
            assertEquals(size + 1, productService.listAllObjects().size());
            assertEquals(product, createProduct);
        } catch (DomainObjectNotFound domainObjectNotFound) {
            assertFalse(true);
        }
        try {
            product.setName("coiso e cenas");
            Product updatedProduct = productService.createOrUpdateObject(product);
            assertEquals(product, updatedProduct);
        } catch (DomainObjectNotFound domainObjectNotFound) {
            assertFalse(true);
        }
    }
}