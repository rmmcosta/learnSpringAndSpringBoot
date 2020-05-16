package com.rmmcosta.MyCrud.services.mapServices;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Product;
import com.rmmcosta.MyCrud.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ActiveProfiles("map")
class ProductMapServiceImplTest {
    @Test
    void listAllProducts() {
        ProductMapServiceImpl productService = new ProductMapServiceImpl();
        assertEquals(4, productService.listAllObjects().size());
    }

    @Test
    void deleteProduct() throws DomainObjectNotFound {
        ProductMapServiceImpl productService = new ProductMapServiceImpl();
        assertEquals(4, productService.listAllObjects().size());
        productService.deleteObject(1);
        assertEquals(3, productService.listAllObjects().size());
    }

    @Test
    void deleteAllProduct() throws DomainObjectNotFound {
        AbstractMapService productService = new ProductMapServiceImpl();
        int size = productService.getCount();
        assertEquals(4, size);
        for (int i = 1; i <= size; i++) {
            productService.deleteObject(i);
            assertEquals(size-i, productService.getCount());
        }
    }

    @Test
    void getId() throws DomainObjectNotFound {
        AbstractMapService productService = new ProductMapServiceImpl();
        int size = productService.getCount();
        for (int i = 1; i <= size; i++) {
            int currId = productService.getObjectById(i).getId();
            assertEquals(i,currId);
        }
    }

    @Test
    void createOrUpdateProduct() {
        ProductService productService = new ProductMapServiceImpl();
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