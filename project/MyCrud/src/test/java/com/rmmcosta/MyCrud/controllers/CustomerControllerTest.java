package com.rmmcosta.MyCrud.controllers;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Cart;
import com.rmmcosta.MyCrud.domain.Customer;
import com.rmmcosta.MyCrud.domain.Product;
import com.rmmcosta.MyCrud.services.CustomerService;
import com.rmmcosta.MyCrud.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CustomerControllerTest {
    @Mock
    private CustomerService customerService;
    @Mock
    private UserService userService;
    @InjectMocks
    private CustomerController customerController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void listAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer());
        customerList.add(new Customer());
        when(customerService.listAllObjects()).thenReturn((List) customerList);
        try {
            mockMvc.perform(get("/customers")).
                    andExpect(status().isOk()).
                    andExpect(view().name("/Customer/customers")).
                    andExpect(model().attribute("customers", hasSize(2)));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void getCustomerById() {
        int id = 10;
        String name = "Customer 10";
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(name);
        try {
            when(customerService.getObjectById(id)).thenReturn(customer);
        } catch (DomainObjectNotFound domainObjectNotFound) {
            assertFalse(true);
        }
        try {
            mockMvc.perform(get("/customer/"+id)).
                    andExpect(status().isOk()).
                    andExpect(view().name("/Customer/customer")).
                    andExpect(model().attribute("customer", instanceOf(Customer.class))).
                    andExpect(model().attribute("customer", hasProperty("id", is(id)))).
                    andExpect(model().attribute("customer", hasProperty("firstName", is(name))));
        } catch (Exception e) {
            assertFalse(true);
        }
    }

    @Test
    void deleteCustomer() {
        int id = 1;
        try {
            mockMvc.perform(get("/customer/delete/"+id)).
                    andExpect(status().isFound()).
                    andExpect(view().name("redirect:/customers"));
        } catch (Exception e) {
            assertFalse(true);
        }
        try {
            verify(customerService, times(1)).deleteObject(id);
        } catch (DomainObjectNotFound domainObjectNotFound) {
            System.out.println("error verifying delete: " + domainObjectNotFound.getMessage());
            assertFalse(true);
        }
    }

    @Test
    void editCustomer() {
        int id = 10;
        String name = "Customer 10";
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(name);
        try {
            when(customerService.getObjectById(id)).thenReturn(customer);
        } catch (DomainObjectNotFound domainObjectNotFound) {
            assertFalse(true);
        }
        try {
            mockMvc.perform(get("/customer/edit/"+id)).
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
       int id = 100;
        String address = "Paço da Rainha, n.º22, 2º";
        String city = "Lisbon";
        String country = "Portugal";
        String email = "rmmcosta@mail.com";
        String firstName = "Ricardo";
        String lastName = "Costa";
        String phoneNumber = "938012420";
        String state = "Lisbon";
        String zipCode = "1150-246";
        Customer customer = new Customer();
        customer.setId(id);
        customer.setAddress(address);
        customer.setCity(city);
        customer.setCountry(country);
        customer.setEmail(email);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPhoneNumber(phoneNumber);
        customer.setState(state);
        customer.setZipCode(zipCode);
        try {
            when(customerService.createOrUpdateObject(Mockito.any(customer.getClass()))).thenReturn(customer);
        } catch (DomainObjectNotFound domainObjectNotFound) {
            System.out.println("error mocking:" + domainObjectNotFound.getMessage());
            assertFalse(true);
        }
        try {
            mockMvc.perform(post("/customer")
                    .param("id", String.valueOf(id))
                    .param("firstName", firstName)
                    .param("lastName", lastName)
                    .param("address", address)
                    .param("phoneNumber", phoneNumber)
                    .param("email", email)
                    .param("state", state)
                    .param("city", city)
                    .param("country", country)
                    .param("zipCode", zipCode)
            )
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:/customer/" + id))
                    .andExpect(model().attribute("customer", hasProperty("id", is(id))))
                    .andExpect(model().attribute("customer", hasProperty("firstName", is(firstName))))
                    .andExpect(model().attribute("customer", hasProperty("email", is(email))));
        } catch (Exception e) {
            System.out.println("error performing:" + e.getMessage());
            assertFalse(true);
        }

        ArgumentCaptor<Customer> boundCustomer = ArgumentCaptor.forClass(Customer.class);
        try {
            verify(customerService).createOrUpdateObject(boundCustomer.capture());
            assertEquals(id, boundCustomer.getValue().getId());
            assertEquals(firstName, boundCustomer.getValue().getFirstName());
        } catch (DomainObjectNotFound domainObjectNotFound) {
            System.out.println("error bounding: " + domainObjectNotFound.getMessage());
            assertFalse(true);
        }
    }
}