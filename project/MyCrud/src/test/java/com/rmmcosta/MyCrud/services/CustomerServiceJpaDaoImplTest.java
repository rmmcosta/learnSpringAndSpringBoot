package com.rmmcosta.MyCrud.services;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("jpadao")
class CustomerServiceJpaDaoImplTest {
    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Test
    void listAllObjects() {
        List<Customer> customerList = (List<Customer>) customerService.listAllObjects();
        assertEquals(2, customerList.size());
    }

    @Test
    void getObjectById() throws DomainObjectNotFound {
        List<Customer> customerList = (List<Customer>) customerService.listAllObjects();
        Customer customer = customerService.getObjectById(customerList.get(0).getId());
        assertEquals(customerList.get(0).getFirstName(), customer.getFirstName());
    }

    @Test
    void createOrUpdateObject() throws DomainObjectNotFound {
        List<Customer> customerList = (List<Customer>) customerService.listAllObjects();
        int initSize = customerList.size();
        Customer customer = new Customer();
        customer.setAddress("Paço da Rainha, n.º22, 2º");
        customer.setCity("Lisbon");
        customer.setCountry("Portugal");
        customer.setEmail("aramos@mail.com");
        customer.setFirstName("Ana");
        customer.setLastName("Ramos");
        customer.setPhoneNumber("966945019");
        customer.setState("Lisbon");
        customer.setZipCode("1150-246");
        customerService.createOrUpdateObject(customer);
        customerList = (List<Customer>) customerService.listAllObjects();
        assertEquals(initSize+1,customerList.size());
    }

    @Test
    void deleteObject() throws DomainObjectNotFound {
        List<Customer> customerList = (List<Customer>) customerService.listAllObjects();
        int initSize = customerList.size();
        customerService.deleteObject(customerList.get(0).getId());
        customerList = (List<Customer>) customerService.listAllObjects();
        assertEquals(initSize-1,customerList.size());
    }
}