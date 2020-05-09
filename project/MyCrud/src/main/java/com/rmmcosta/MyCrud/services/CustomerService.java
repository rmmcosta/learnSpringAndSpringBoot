package com.rmmcosta.MyCrud.services;

import com.rmmcosta.MyCrud.customExceptions.CustomerNotFound;
import com.rmmcosta.MyCrud.domain.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> listAllCustomers();
    Customer getCustomerById(int id) throws CustomerNotFound;
    Customer createOrUpdateCustomer(Customer customer) throws CustomerNotFound;
    void deleteCustomer(int id) throws CustomerNotFound;
    int getNextKey();
}
