package com.rmmcosta.XMLConfiguration.services;

public class HelloFactory {
    public HelloService createHelloService(String language) {
        return switch (language) {
            case "pt" -> new HelloWorldPtImpl();
            case "fr" -> new HelloWorldFrImpl();
            default -> new HelloWorldEnImpl();
        };
    }
}
