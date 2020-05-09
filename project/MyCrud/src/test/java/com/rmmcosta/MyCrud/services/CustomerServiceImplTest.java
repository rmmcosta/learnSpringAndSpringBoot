package com.rmmcosta.MyCrud.services;

import com.rmmcosta.MyCrud.customExceptions.CustomerNotFound;
import com.rmmcosta.MyCrud.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {

    @Test
    void listAllCustomers() {
        CustomerService customerService = new CustomerServiceImpl();
        int size = customerService.listAllCustomers().size();
        assertEquals(2, size);
    }

    @Test
    void getCustomerById() {
        CustomerService customerService = new CustomerServiceImpl();
        int size = customerService.listAllCustomers().size();
        for (int i = 1; i <= size; i++) {
            try {
                assertEquals(i, customerService.getCustomerById(i).getId());
            } catch (CustomerNotFound customerNotFound) {
                assertFalse(true);
            }
        }
    }

    @Test
    void createOrUpdateCustomer() {
        CustomerService customerService = new CustomerServiceImpl();
        int size = customerService.listAllCustomers().size();
        Customer customer = new Customer();
        customer.setAddress("Paço da Rainha, n.º22, 2º");
        customer.setCity("Lisbon");
        customer.setCountry("Portugal");
        customer.setEmail("rmmcosta@mail.com");
        customer.setFirstName("Ricardo");
        customer.setLastName("Costa");
        customer.setPhoneNumber("938012420");
        customer.setState("Lisbon");
        customer.setZipCode("1150-246");
        try {
            Customer createdCustomer = customerService.createOrUpdateCustomer(customer);
            customer.setId(createdCustomer.getId());
            assertEquals(size + 1, customerService.listAllCustomers().size());
            assertEquals(customer, createdCustomer);
        } catch (CustomerNotFound customerNotFound) {
            assertFalse(true);
        }
        try {
            customer.setPhoneNumber("914423167");
            Customer updatedCustomer = customerService.createOrUpdateCustomer(customer);
            assertEquals(customer, updatedCustomer);
        } catch (CustomerNotFound customerNotFound) {
            assertFalse(true);
        }
    }

    @Test
    void deleteCustomer() {
        CustomerService customerService = new CustomerServiceImpl();
        int size = customerService.listAllCustomers().size();
        try {
            customerService.deleteCustomer(1);
            assertEquals(size-1,customerService.listAllCustomers().size());
            customerService.deleteCustomer(2);
            assertEquals(size-2,customerService.listAllCustomers().size());
        } catch (CustomerNotFound customerNotFound) {
            assertFalse(true);
        }
    }

    @Test
    void getNextKey() {
        CustomerService customerService = new CustomerServiceImpl();
        int size = customerService.listAllCustomers().size();
        assertEquals(size+1, customerService.getNextKey());
    }

    @Test
    void CustomerNotFound() throws CustomerNotFound {
        CustomerService customerService = new CustomerServiceImpl();
        assertThrows(CustomerNotFound.class,()->customerService.getCustomerById(100));
        assertThrows(CustomerNotFound.class, () -> customerService.deleteCustomer(500));
        Customer newCustomer = new Customer();
        newCustomer.setId(333);
        assertThrows(CustomerNotFound.class, () -> customerService.createOrUpdateCustomer(newCustomer));
    }
}