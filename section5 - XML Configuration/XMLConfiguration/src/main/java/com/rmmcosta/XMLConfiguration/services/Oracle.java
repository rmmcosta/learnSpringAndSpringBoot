package com.rmmcosta.XMLConfiguration.services;

public class Oracle implements Database {
    @Override
    public int open() {
        System.out.println("Opening Oracle database...");
        return 1;
    }
}
