package com.rmmcosta.MyCrud.bootstrap;

import com.rmmcosta.MyCrud.customExceptions.DomainObjectNotFound;
import com.rmmcosta.MyCrud.domain.Customer;
import com.rmmcosta.MyCrud.domain.Product;
import com.rmmcosta.MyCrud.services.CustomerService;
import com.rmmcosta.MyCrud.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private ProductService productService;
    private CustomerService customerService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            bootstrapProducts();
        } catch (DomainObjectNotFound domainObjectNotFound) {
            domainObjectNotFound.printStackTrace();
        }
        try {
            bootstrapCustomers();
        } catch (DomainObjectNotFound domainObjectNotFound) {
            domainObjectNotFound.printStackTrace();
        }
    }

    private void bootstrapCustomers() throws DomainObjectNotFound {
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
        customerService.createOrUpdateObject(customer);

        customer = new Customer();
        customer.setAddress("Travessa de Matos, n.º115");
        customer.setCity("Guimarães");
        customer.setCountry("Portugal");
        customer.setEmail("costa@mail.com");
        customer.setFirstName("Rui");
        customer.setLastName("Costa");
        customer.setPhoneNumber("910274474");
        customer.setState("Braga");
        customer.setZipCode("4765-571");
        customerService.createOrUpdateObject(customer);
    }

    private void bootstrapProducts() throws DomainObjectNotFound {
        Product product;

        product = new Product();
        product.setName("Samsung S10");
        product.setDescription("Smartphone running Android 9.");
        product.setPrice(new BigDecimal("679.99"));
        product.setImageUrl("https://www.worten.pt/i/af6e5cad58f7001b1ebb0e536e45dc12c0d2a76c.jpg");
        product.setCreatedOn(new Date());
        productService.createOrUpdateObject(product);

        product = new Product();
        product.setName("iPhone XR");
        product.setDescription("Smartphone running iOS 12.");
        product.setPrice(new BigDecimal("739.99"));
        product.setImageUrl("https://www.worten.pt/i/66a71615da0f9ca5c10be0d34843e7ccfcb1cef9.jpg");
        product.setCreatedOn(new Date());
        productService.createOrUpdateObject(product);

        product = new Product();
        product.setName("Samsung S20");
        product.setDescription("Smartphone running Android 10.");
        product.setPrice(new BigDecimal("929.99"));
        product.setImageUrl("https://www.worten.pt/i/c753e9bac4f1a6d0cbe8a2be63134768f1c29e0a.jpg");
        product.setCreatedOn(new Date());
        productService.createOrUpdateObject(product);

        product = new Product();
        product.setName("iPhone 11");
        product.setDescription("Smartphone running iOS 13.");
        product.setPrice(new BigDecimal("829.99"));
        product.setImageUrl("https://www.worten.pt/i/ce3be0c610c24f2929c82cc6c04f2aa5ddb90189.jpg");
        product.setCreatedOn(new Date());
        productService.createOrUpdateObject(product);
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
