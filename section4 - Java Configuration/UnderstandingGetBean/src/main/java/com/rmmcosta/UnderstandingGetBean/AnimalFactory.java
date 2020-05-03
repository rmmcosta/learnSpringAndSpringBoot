package com.rmmcosta.UnderstandingGetBean;

public class AnimalFactory {
    private Animal animal;

    public AnimalFactory(String animalType, String name) {
        this.animal = switch (animalType){
            case "tiger" -> new Tiger(name);
            case "lion" -> new Lion(name);
            default -> throw new IllegalStateException("Unexpected value: " + animalType);
        };
    }

    public Animal getAnimal() {
        return animal;
    }
}
