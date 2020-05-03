package com.rmmcosta.DependencyInjection.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.rmmcosta.DependencyInjection.services.GreetingService;

@Controller
public class GreetingController {
    private GreetingService greetingService;

    @Autowired
    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String getMessage() {
        return greetingService.greet();
    }
}
