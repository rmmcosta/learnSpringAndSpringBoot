package com.rmmcosta.HelloWorld;

import org.springframework.stereotype.Component;

@Component
public class HelloWorld implements GreetingService, HelloService {
    @Override
    public void greet(String who) {
        System.out.println("Hello world! Welcome " + who);
    }

    @Override
    public void sayHello() {
        System.out.println("Hello World!");
    }
}
