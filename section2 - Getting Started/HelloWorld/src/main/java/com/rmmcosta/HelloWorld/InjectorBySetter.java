package com.rmmcosta.HelloWorld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InjectorBySetter implements Injector {
    private HelloService helloService;
    @Override
    public void message() {
        helloService.sayHello();
    }
    @Autowired
    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }
}
