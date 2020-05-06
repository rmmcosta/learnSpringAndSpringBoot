package com.rmmcosta.XMLConfiguration.services;

public class HelloWorldFrImpl implements HelloService {
    @Override
    public String getGreeting() {
        return "Bonjour le monde!";
    }
}
