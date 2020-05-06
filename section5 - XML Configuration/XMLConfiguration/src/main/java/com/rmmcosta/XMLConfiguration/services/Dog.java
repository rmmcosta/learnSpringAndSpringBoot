package com.rmmcosta.XMLConfiguration.services;

public class Dog extends Animal {
    protected Dog(String name) {
        super(name);
    }

    @Override
    public void makeNoise() {
        System.out.println("Augh!");
    }
}
