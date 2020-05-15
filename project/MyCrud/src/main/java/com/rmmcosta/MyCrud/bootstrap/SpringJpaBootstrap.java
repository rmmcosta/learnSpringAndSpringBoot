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
        for (Customer customer : BootstrapCustomers.getBootstrapCustomers()) {
            customerService.createOrUpdateObject(customer);
        }
    }

    private void bootstrapProducts() throws DomainObjectNotFound {
        for (Product product : BootstrapProducts.getBootstrapProducts()) {
            productService.createOrUpdateObject(product);
        }
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
