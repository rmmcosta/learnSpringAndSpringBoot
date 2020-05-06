package com.rmmcosta.XMLConfiguration.services;

public class MySql implements Database {
    @Override
    public int open() {
        System.out.println("Opening MySql database...");
        return 1;
    }
}
