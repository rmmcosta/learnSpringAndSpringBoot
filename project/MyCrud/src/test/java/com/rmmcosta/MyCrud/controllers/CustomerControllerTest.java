package com.rmmcosta.MyCrud.controllers;

import com.rmmcosta.MyCrud.services.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class CustomerControllerTest {
    private MockMvc mockMvc;
    private CustomerController customerController;
    @BeforeEach
    void setUp() {
        customerController = new CustomerController();
        customerController.setCustomerService(new CustomerServiceImpl());
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void listAllCustomers() {
        try {
            mockMvc.perform(get("/customers")).
                    andExpect(status().isOk()).
                    andExpect(view().name("/Customer/customers"));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void getCustomerById() {
        try {
            mockMvc.perform(get("/customer/1")).
                    andExpect(status().isOk()).
                    andExpect(view().name("/Customer/customer"));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void deleteCustomer() {
        try {
            mockMvc.perform(get("/customer/delete/1")).
                    andExpect(status().isFound()).
                    andExpect(view().name("redirect:/customers"));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void editCustomer() {
        try {
            mockMvc.perform(get("/customer/edit/1")).
                    andExpect(status().isOk()).
                    andExpect(view().name("/Customer/newCustomer"));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void newCustomer() {
        try {
            mockMvc.perform(get("/customer/new")).
                    andExpect(status().isOk()).
                    andExpect(view().name("/Customer/newCustomer"));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void createOrUpdateCustomer() {
        try {
            mockMvc.perform(post("/customer")).
                    andExpect(status().isFound());
        } catch (Exception e) {
            assertFalse(true);
        }
    }
}