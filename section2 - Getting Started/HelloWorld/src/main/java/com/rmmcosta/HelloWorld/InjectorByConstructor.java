package com.rmmcosta.HelloWorld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InjectorByConstructor implements Injector {
    private HelloService helloService;

    @Autowired
    public InjectorByConstructor(HelloService helloService) {
        this.helloService = helloService;
    }

    public void message() {
        helloService.sayHello();
    }
}
