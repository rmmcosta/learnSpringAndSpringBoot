package com.rmmcosta.MyCrud.services.mapServices;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Customer;
import com.rmmcosta.MyCrud.domain.User;
import com.rmmcosta.MyCrud.services.CustomerService;
import com.rmmcosta.MyCrud.services.UserService;
import com.rmmcosta.MyCrud.services.mapServices.AbstractMapService;
import com.rmmcosta.MyCrud.services.mapServices.CustomerMapServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("map")
class CustomerMapServiceImplTest {

    @Test
    void listAllCustomers() {
        CustomerService customerService = new CustomerMapServiceImpl();
        int size = customerService.listAllObjects().size();
        assertEquals(2, size);
    }

    @Test
    void getCustomerById() {
        CustomerService customerService = new CustomerMapServiceImpl();
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
        CustomerService customerService = new CustomerMapServiceImpl();
        int size = customerService.listAllObjects().size();
        Customer customer = new Customer();
        customer.setEmail("rmmcosta@mail.com");
        customer.setFirstName("Ricardo");
        customer.setLastName("Costa");
        customer.setPhoneNumber("938012420");
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
        CustomerService customerService = new CustomerMapServiceImpl();
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
        AbstractMapService customerService = new CustomerMapServiceImpl();
        int size = customerService.listAllObjects().size();
        assertEquals(size + 1, customerService.getNextKey());
    }

    @Test
    void CustomerNotFound() throws DomainObjectNotFound {
        CustomerService customerService = new CustomerMapServiceImpl();
        assertThrows(DomainObjectNotFound.class, () -> customerService.getObjectById(100));
        assertThrows(DomainObjectNotFound.class, () -> customerService.getObjectById(500));
    }
}