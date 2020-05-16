package com.rmmcosta.MyCrud.services.jpaServices;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Customer;
import com.rmmcosta.MyCrud.domain.Product;
import com.rmmcosta.MyCrud.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("jpadao")
class ProductServiceJpaDaoImplTest {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Test
    void listAllObjects() {
        List<Product> productList = (List<Product>) productService.listAllObjects();
        assertEquals(4, productList.size());
    }

    @Test
    void getObjectById() {
        List<Product> productList = (List<Product>) productService.listAllObjects();
        Product product = productList.get(0);
        assertEquals(productList.get(0).getName(), product.getName());
    }

    @Test
    void createOrUpdateObject() throws DomainObjectNotFound {
        List<Product> productList = (List<Product>) productService.listAllObjects();
        int initSize = productList.size();
        Product product = new Product();
        product.setName("Jóia");
        product.setDescription("a jóia do nilo");
        product.setPrice(new BigDecimal("679.99"));
        product.setImageUrl("https://cenas");
        product.setCreatedOn(new Date());
        productService.createOrUpdateObject(product);
        productList = (List<Product>) productService.listAllObjects();
        assertEquals(initSize+1, productList.size());
    }

    @Test
    void deleteObject() throws DomainObjectNotFound {
        List<Product> productList = (List<Product>) productService.listAllObjects();
        int initSize = productList.size();
        productService.deleteObject(productList.get(0).getId());
        productList = (List<Product>) productService.listAllObjects();
        assertEquals(initSize-1,productList.size());
    }
}