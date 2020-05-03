package com.rmmcosta.DependencyInjection.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"default", "en"})
public class HelloWorldEn implements GreetingService {
    @Override
    public String greet() {
        return "Hello World!";
    }
}
