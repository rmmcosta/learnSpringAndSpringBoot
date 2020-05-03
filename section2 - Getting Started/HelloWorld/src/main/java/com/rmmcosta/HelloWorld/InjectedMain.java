package com.rmmcosta.HelloWorld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class InjectedMain {
    private static ApplicationContext ctx;

    public static void main(String[] args) {
        ctx = SpringApplication.run(InjectedMain.class, args);
        InjectorByConstructor service = ctx.getBean(InjectorByConstructor.class);
        service.message();
        InjectorBySetter setter = ctx.getBean(InjectorBySetter.class);
        setter.message();
    }
}
