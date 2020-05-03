package com.rmmcosta.UnderstandingGetBean;

public class Lion implements Animal {
    private final String name;

    public Lion(String name) {
        this.name = name;
    }

    public String getName() {
        return "Lion:" + name;
    }
}
