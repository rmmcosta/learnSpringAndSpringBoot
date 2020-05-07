package com.rmmcosta.XMLConfiguration.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:spring/hello-config.xml"})
class HelloServiceTest {

    @Autowired
    HelloService helloService;

    @Test
    void getGreeting() {
        String greeting = helloService.getGreeting();
        assertEquals("Hello World!", greeting);
    }
}