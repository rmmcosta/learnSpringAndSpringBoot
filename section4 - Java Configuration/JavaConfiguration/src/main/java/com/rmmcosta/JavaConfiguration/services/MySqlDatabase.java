package com.rmmcosta.JavaConfiguration.services;

public class MySqlDatabase implements DatabaseService {
    @Override
    public String connect() {
        return "Connecting to MySql...";
    }
}
