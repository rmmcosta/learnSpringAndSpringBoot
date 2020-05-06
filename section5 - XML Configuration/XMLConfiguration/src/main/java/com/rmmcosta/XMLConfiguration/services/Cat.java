package com.rmmcosta.XMLConfiguration.services;

public class Cat extends Animal {
    protected Cat(String name) {
        super(name);
    }

    @Override
    public void makeNoise() {
        System.out.println("Miau!");
    }
}
