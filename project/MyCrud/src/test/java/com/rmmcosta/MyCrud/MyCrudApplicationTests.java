package com.rmmcosta.MyCrud;

import com.rmmcosta.MyCrud.controllers.CustomerController;
import com.rmmcosta.MyCrud.controllers.IndexController;
import com.rmmcosta.MyCrud.controllers.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MyCrudApplicationTests {
    @Autowired
    private IndexController indexController;
    @Autowired
    private CustomerController customerController;
    @Autowired
    private ProductController productController;

    @Test
    void contextLoads() {
        assertThat(indexController).isNotNull();
        assertThat(customerController).isNotNull();
        assertThat(productController).isNotNull();
    }

}
