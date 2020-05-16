package com.rmmcosta.MyCrud.services.jpaServices;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Customer;
import com.rmmcosta.MyCrud.domain.User;
import com.rmmcosta.MyCrud.services.CustomerService;
import com.rmmcosta.MyCrud.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@ActiveProfiles("jpadao")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class CustomerServiceJpaDaoImplTest {
    private CustomerService customerService;
    private UserService userService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Test
    void listAllObjects() {
        assertEquals(2, customerService.listAllObjects().size());
    }

    @Test
    void getObjectById() throws DomainObjectNotFound {
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
        Customer newCustomer = customerService.createOrUpdateObject(customer);
        List<Customer> customerList = (List<Customer>) customerService.listAllObjects();
        customer = customerService.getObjectById(customerList.get(0).getId());
        assertEquals(customerList.get(0).getFirstName(), customer.getFirstName());
        customerService.deleteObject(newCustomer.getId());
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
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
        Customer newCustomer = customerService.createOrUpdateObject(customer);
        customerList = (List<Customer>) customerService.listAllObjects();
        assertEquals(initSize+1,customerList.size());
        customerService.deleteObject(newCustomer.getId());
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
            customerService.deleteObject(newCustomer.getId());
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