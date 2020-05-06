package com.rmmcosta.XMLConfiguration.services;

public class DogFactory implements AnimalFactory {
    private final String name;

    public DogFactory(String name) {
        this.name = name;
    }

    @Override
    public Animal getAnimal() {
        return new Dog(name);
    }
}
