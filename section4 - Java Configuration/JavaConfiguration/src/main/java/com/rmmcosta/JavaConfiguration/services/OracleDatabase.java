package com.rmmcosta.JavaConfiguration.services;

public class OracleDatabase implements DatabaseService {
    @Override
    public String connect() {
        return "Connecting to Oracle...";
    }
}
