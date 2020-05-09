package com.rmmcosta.MyCrud.services;

import com.rmmcosta.MyCrud.customExceptions.CustomerNotFound;
import com.rmmcosta.MyCrud.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    private Map<Integer, Customer> customers;

    public CustomerServiceImpl() {
        bootstrapCustomers();
    }

    private void bootstrapCustomers() {
        customers = new HashMap<>();
        Customer customer = new Customer();
        customer.setAddress("Paço da Rainha, n.º22, 2º");
        customer.setCity("Lisbon");
        customer.setCountry("Portugal");
        customer.setEmail("rmmcosta@mail.com");
        customer.setFirstName("Ricardo");
        customer.setLastName("Costa");
        customer.setPhoneNumber("938012420");
        customer.setId(1);
        customer.setState("Lisbon");
        customer.setZipCode("1150-246");
        customers.put(1, customer);

        customer = new Customer();
        customer.setAddress("Travessa de Matos, n.º115");
        customer.setCity("Guimarães");
        customer.setCountry("Portugal");
        customer.setEmail("costa@mail.com");
        customer.setFirstName("Rui");
        customer.setLastName("Costa");
        customer.setPhoneNumber("910274474");
        customer.setId(2);
        customer.setState("Braga");
        customer.setZipCode("4765-571");
        customers.put(2, customer);
    }

    @Override
    public List<Customer> listAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Customer getCustomerById(int id) throws CustomerNotFound {
        if (customers.containsKey(id)) {
            return customers.get(id);
        } else {
            throw new CustomerNotFound();
        }
    }

    @Override
    public Customer createOrUpdateCustomer(Customer customer) throws CustomerNotFound {
        if (customer.getId() == 0) {
            return createCustomer(customer);
        } else {
            return updateCustomer(customer);
        }
    }

    private Customer createCustomer(Customer customer) {
        Customer newCustomer = new Customer();
        customer.setAddress(customer.getAddress());
        customer.setCity(customer.getCity());
        customer.setCountry(customer.getCountry());
        customer.setEmail(customer.getEmail());
        customer.setFirstName(customer.getFirstName());
        customer.setLastName(customer.getLastName());
        customer.setPhoneNumber(customer.getPhoneNumber());
        customer.setId(getNextKey());
        customer.setState(customer.getState());
        customer.setZipCode(customer.getZipCode());
        customers.put(getNextKey(), customer);
        return customer;
    }

    private Customer updateCustomer(Customer customer) throws CustomerNotFound {
        if (customers.containsKey(customer.getId())) {
            Customer updateCustomer = customers.get(customer.getId());
            updateCustomer.setAddress(customer.getAddress());
            updateCustomer.setCity(customer.getCity());
            updateCustomer.setCountry(customer.getCountry());
            updateCustomer.setEmail(customer.getEmail());
            updateCustomer.setFirstName(customer.getFirstName());
            updateCustomer.setLastName(customer.getLastName());
            updateCustomer.setPhoneNumber(customer.getPhoneNumber());
            updateCustomer.setState(customer.getState());
            updateCustomer.setZipCode(customer.getZipCode());
            return updateCustomer;
        } else {
            throw new CustomerNotFound();
        }
    }

    @Override
    public void deleteCustomer(int id) throws CustomerNotFound {
        if (customers.containsKey(id)) {
            customers.remove(id);
        } else {
            throw new CustomerNotFound();
        }
    }

    @Override
    public int getNextKey() {
        return customers.size()+1;
    }
}
