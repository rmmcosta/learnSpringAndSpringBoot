package com.rmmcosta.MyCrud.controllers;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Product;
import com.rmmcosta.MyCrud.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void listProducts() {
        try {
            List<Product> productList = new ArrayList<>();
            productList.add(new Product());
            when(productService.listAllObjects()).thenReturn((List) productList);
            mockMvc.perform(get("/products")).
                    andExpect(status().isOk()).
                    andExpect(view().name("/Product/products")).
                    andExpect(model().attribute("products", hasSize(1)));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void getProduct() {
        int id = 10;
        String name = "Product 10";
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        try {
            when(productService.getObjectById(10)).thenReturn(product);
        } catch (DomainObjectNotFound domainObjectNotFound) {
            assertFalse(true);
        }
        try {
            mockMvc.perform(get("/product/10")).
                    andExpect(status().isOk()).
                    andExpect(view().name("/Product/product")).
                    andExpect(model().attribute("product", instanceOf(Product.class))).
                    andExpect(model().attribute("product", hasProperty("id", is(id)))).
                    andExpect(model().attribute("product", hasProperty("name", is(name))));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void newProduct() {
        verifyNoInteractions(productService);
        try {
            mockMvc.perform(get("/product/new")).
                    andExpect(status().isOk()).
                    andExpect(view().name("/Product/newProduct")).
                    andExpect(model().attribute("product", instanceOf(Product.class)));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void editProduct() {
        try {
            mockMvc.perform(get("/product/edit/1")).
                    andExpect(status().isOk()).
                    andExpect(view().name("/Product/newProduct"));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void createOrUpdateProduct() {
        int id = 100;
        String name = "Produto 100", description = "the description", imageUrl = "the image";
        BigDecimal price = new BigDecimal("4.50");
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);

        try {
            when(productService.createOrUpdateObject(Mockito.any(product.getClass()))).thenReturn(product);
        } catch (DomainObjectNotFound domainObjectNotFound) {
            System.out.println("when error: " + domainObjectNotFound.getMessage());
            assertFalse(true);
        }
        try {
            mockMvc.perform(post("/product")
                    .param("id", String.valueOf(id))
                    .param("name", name)
                    .param("description", description)
                    .param("price", String.valueOf(price))
                    .param("imageUrl", imageUrl)).
                    andExpect(status().is3xxRedirection()).
                    andExpect(view().name("redirect:/product/" + id)).
                    andExpect(model().attribute("product", instanceOf(Product.class))).
                    andExpect(model().attribute("product", hasProperty("name", is(name)))).
                    andExpect(model().attribute("product", hasProperty("price", is(price))));
        } catch (Exception e) {
            System.out.println("perform error:" + e.getMessage());
            assertFalse(true);
        }

        ArgumentCaptor<Product> boundProduct = ArgumentCaptor.forClass(Product.class);
        try {
            verify(productService).createOrUpdateObject(boundProduct.capture());
            assertEquals(id, boundProduct.getValue().getId());
            assertEquals(name, boundProduct.getValue().getName());
            assertEquals(price, boundProduct.getValue().getPrice());
        } catch (DomainObjectNotFound domainObjectNotFound) {
            System.out.println("error bounding: " + domainObjectNotFound.getMessage());
            assertFalse(true);
        }
    }

    @Test
    void deleteProduct() {
        int id = 1;
        try {
            mockMvc.perform(get("/product/delete/" + id)).
                    andExpect(status().isFound()).
                    andExpect(view().name("redirect:/products"));
        } catch (Exception e) {
            assertFalse(true);
        }
        try {
            verify(productService, times(1)).deleteObject(id);
        } catch (DomainObjectNotFound domainObjectNotFound) {
            System.out.println("error verifying delete: " + domainObjectNotFound.getMessage());
            assertFalse(true);
        }
    }
}