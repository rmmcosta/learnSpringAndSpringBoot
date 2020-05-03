package com.rmmcosta.JavaConfiguration.controllers;

import com.rmmcosta.JavaConfiguration.services.DatabaseFactory;
import com.rmmcosta.JavaConfiguration.services.MySqlFactory;
import com.rmmcosta.JavaConfiguration.services.OracleFactory;

public class FactoryController {
    DatabaseFactory databaseFactory;

    public FactoryController() {
        this("mysql");
    }

    public DatabaseFactory getDatabaseFactory() {
        return databaseFactory;
    }

    public FactoryController(String provider) {
        this.databaseFactory = switch(provider){
            case "mysql" -> new MySqlFactory();
            case "oracle" -> new OracleFactory();
            default -> throw new IllegalStateException("Unexpected value: " + provider);
        };
    }
    public void connect() {
        System.out.println(databaseFactory.createDatabase().connect());
    }
}
