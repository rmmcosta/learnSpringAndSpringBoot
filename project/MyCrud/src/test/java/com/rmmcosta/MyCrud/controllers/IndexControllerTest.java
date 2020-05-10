package com.rmmcosta.MyCrud.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerTest {
    private MockMvc mockMvc;
    private IndexController indexController;

    @BeforeEach
    void setUp() {
        indexController = new IndexController();
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
    }

    @Test
    void index() {
        try {
            mockMvc.perform(get("/")).
                    andExpect(status().isOk()).
                    andExpect(view().name("index"));
        } catch (Exception e) {
            assertFalse(true);
        }
    }
}