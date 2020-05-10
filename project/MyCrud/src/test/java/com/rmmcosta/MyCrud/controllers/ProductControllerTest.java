package com.rmmcosta.MyCrud.controllers;

import com.rmmcosta.MyCrud.services.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class ProductControllerTest {
    private MockMvc mockMvc;
    private ProductController productController;

    @BeforeEach
    void setUp() {
        productController = new ProductController();
        productController.setProductService(new ProductServiceImpl());
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void listProducts() {
        try {
            mockMvc.perform(get("/products")).
                    andExpect(status().isOk()).
                    andExpect(view().name("/Product/products"));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void getProduct() {
        try {
            mockMvc.perform(get("/product/1")).
                    andExpect(status().isOk()).
                    andExpect(view().name("/Product/product"));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void newProduct() {
        try {
            mockMvc.perform(get("/product/new")).
                    andExpect(status().isOk()).
                    andExpect(view().name("/Product/newProduct"));
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
        try {
            mockMvc.perform(post("/product")).
                    andExpect(status().isFound());
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void deleteProduct() {
        try {
            mockMvc.perform(get("/product/delete/1")).
                    andExpect(status().isFound()).
                    andExpect(view().name("redirect:/products"));
        } catch (Exception e) {
            assertFalse(true);
        }
    }
}