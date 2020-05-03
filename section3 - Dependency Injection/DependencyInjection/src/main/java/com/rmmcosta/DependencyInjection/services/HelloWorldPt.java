package com.rmmcosta.DependencyInjection.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("pt")
public class HelloWorldPt implements GreetingService {
    @Override
    public String greet() {
        return "Olá Mundo!";
    }
}
