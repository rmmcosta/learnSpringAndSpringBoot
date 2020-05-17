package com.rmmcosta.MyCrud.bootstrap;

import com.rmmcosta.MyCrud.domain.Address;
import com.rmmcosta.MyCrud.domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class BootstrapCustomers {
    public static List<Customer> getBootstrapCustomers() {
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        Address billingAddress = new Address();
        Address shippingAddress = new Address();
        //billing address
        billingAddress.setAddress("Paço da Rainha, n.º22, 2º");
        billingAddress.setCity("Lisbon");
        billingAddress.setCountry("Portugal");
        billingAddress.setState("Lisbon");
        billingAddress.setZipCode("1150-246");
        //shipping address
        shippingAddress.setAddress("Travessa de Matos");
        shippingAddress.setCity("Guimarães");
        shippingAddress.setCountry("Portugal");
        shippingAddress.setState("Braga");
        shippingAddress.setZipCode("4765-571");
        customer.setBillingAddress(billingAddress);
        customer.setShippingAddress(shippingAddress);
        customer.setEmail("rmmcosta@mail.com");
        customer.setId(1);
        customer.setFirstName("Ricardo");
        customer.setLastName("Costa");
        customer.setPhoneNumber("938012420");
        customerList.add(customer);

        customer = new Customer();
        customer.setBillingAddress(billingAddress);
        customer.setShippingAddress(shippingAddress);
        customer.setId(2);
        customer.setEmail("costa@mail.com");
        customer.setFirstName("Rui");
        customer.setLastName("Costa");
        customer.setPhoneNumber("910274474");
        customerList.add(customer);

        return customerList;
    }
}
