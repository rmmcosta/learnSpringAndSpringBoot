package com.rmmcosta.MyCrud.services.jpaServices;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Customer;
import com.rmmcosta.MyCrud.domain.User;
import com.rmmcosta.MyCrud.services.CustomerService;
import com.rmmcosta.MyCrud.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@ActiveProfiles("jpadao")
class CustomerServiceJpaDaoImplTest {
    private CustomerService customerService;
    private UserService userService;

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
    void createOrUpdateObjectWithUser() throws DomainObjectNotFound {
        List<Customer> customerList = (List<Customer>) customerService.listAllObjects();
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

        User user = (User) userService.listAllObjects().get(0);
        customer.setUserId(user.getId());
        Customer newCustomer = customerService.createOrUpdateObject(customer);

        assert newCustomer.getUser().getIsActive();
        assert newCustomer.getUser().getId() != 0;
        assert !newCustomer.getUser().getEncryptedPassword().isEmpty();
        customerService.deleteObject(newCustomer.getId());
    }

    @Test
    void createOrUpdateObjectWithNewUser() throws DomainObjectNotFound {
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

        User user = new User();
        String username = "aramos";
        user.setUsername(username);
        user.setPassword("123456");
        User newUser = userService.createOrUpdateObject(user);
        customer.setUserId(newUser.getId());
        customer.setUser(user);
        Customer newCustomer = customerService.createOrUpdateObject(customer);

        assert newCustomer.getUser().getIsActive();
        assert newCustomer.getUser().getId() != 0;
        assert !newCustomer.getUser().getEncryptedPassword().isEmpty();
        customerService.deleteObject(newCustomer.getId());
    }

    @Test
    void editObjectWithUser() {
        Customer customer = (Customer) customerService.listAllObjects().get(0);
        User user = (User) userService.listAllObjects().get(0);
        customer.setUserId(user.getId());
        try {
            Customer newCustomer = customerService.createOrUpdateObject(customer);
            assertEquals(customer.getUserId(), newCustomer.getUserId());
        } catch (DomainObjectNotFound domainObjectNotFound) {
            assertFalse(true);
        }
    }

    @Test
    void deleteObject() throws DomainObjectNotFound {
        List<Customer> customerList = (List<Customer>) customerService.listAllObjects();
        int initSize = customerList.size();
        customerService.deleteObject(customerList.get(0).getId());
        customerList = (List<Customer>) customerService.listAllObjects();
        assertEquals(initSize-1,customerList.size());
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}