package com.rmmcosta.UnderstandingGetBean;

public class Tiger implements Animal {
    private String name;
    public Tiger(String name) {
        this.name = name;
    }

    public String getName() {
        return "Tiger:" + name;
    }
}
