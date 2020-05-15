package com.rmmcosta.MyCrud.services;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("map")
class CustomerServiceImplTest {

    @Test
    void listAllCustomers() {
        CustomerService customerService = new CustomerServiceImpl();
        int size = customerService.listAllObjects().size();
        assertEquals(2, size);
    }

    @Test
    void getCustomerById() {
        CustomerService customerService = new CustomerServiceImpl();
        int size = customerService.listAllObjects().size();
        for (int i = 1; i <= size; i++) {
            try {
                assertEquals(i, customerService.getObjectById(i).getId());
            } catch (DomainObjectNotFound domainObjectNotFound) {
                assertFalse(true);
            }
        }
    }

    @Test
    void createOrUpdateCustomer() {
        CustomerService customerService = new CustomerServiceImpl();
        int size = customerService.listAllObjects().size();
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
            Customer createdCustomer = customerService.createOrUpdateObject(customer);
            customer.setId(createdCustomer.getId());
            assertEquals(size + 1, customerService.listAllObjects().size());
            assertEquals(customer, createdCustomer);
        } catch (DomainObjectNotFound domainObjectNotFound) {
            assertFalse(true);
        }
        try {
            customer.setPhoneNumber("914423167");
            Customer updatedCustomer = customerService.createOrUpdateObject(customer);
            assertEquals(customer, updatedCustomer);
        } catch (DomainObjectNotFound domainObjectNotFound) {
            assertFalse(true);
        }
    }

    @Test
    void deleteCustomer() {
        CustomerService customerService = new CustomerServiceImpl();
        int size = customerService.listAllObjects().size();
        try {
            customerService.deleteObject(1);
            assertEquals(size - 1, customerService.listAllObjects().size());
            customerService.deleteObject(2);
            assertEquals(size - 2, customerService.listAllObjects().size());
        } catch (DomainObjectNotFound domainObjectNotFound) {
            assertFalse(true);
        }
    }

    @Test
    void getNextKey() {
        AbstractService customerService = new CustomerServiceImpl();
        int size = customerService.listAllObjects().size();
        assertEquals(size + 1, customerService.getNextKey());
    }

    @Test
    void CustomerNotFound() throws DomainObjectNotFound {
        CustomerService customerService = new CustomerServiceImpl();
        assertThrows(DomainObjectNotFound.class, () -> customerService.getObjectById(100));
        assertThrows(DomainObjectNotFound.class, () -> customerService.getObjectById(500));
    }
}