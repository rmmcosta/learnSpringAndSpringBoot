package com.rmmcosta.MyCrud.bootstrap;

import com.rmmcosta.MyCrud.domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class BootstrapCustomers {
    public static List<Customer> getBootstrapCustomers() {
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId(1);
        customer.setAddress("Paço da Rainha, n.º22, 2º");
        customer.setCity("Lisbon");
        customer.setCountry("Portugal");
        customer.setEmail("rmmcosta@mail.com");
        customer.setFirstName("Ricardo");
        customer.setLastName("Costa");
        customer.setPhoneNumber("938012420");
        customer.setState("Lisbon");
        customer.setZipCode("1150-246");
        customerList.add(customer);

        customer = new Customer();
        customer.setId(2);
        customer.setAddress("Travessa de Matos, n.º115");
        customer.setCity("Guimarães");
        customer.setCountry("Portugal");
        customer.setEmail("costa@mail.com");
        customer.setFirstName("Rui");
        customer.setLastName("Costa");
        customer.setPhoneNumber("910274474");
        customer.setState("Braga");
        customer.setZipCode("4765-571");
        customerList.add(customer);

        return customerList;
    }
}
