package com.rmmcosta.HelloWorld;

import org.springframework.stereotype.Component;

@Component
public class HelloSimple implements GreetingService {
    @Override
    public void greet(String who) {
        System.out.println("Hello " + who);
    }
}
