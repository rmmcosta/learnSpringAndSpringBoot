package com.rmmcosta.XMLConfiguration.controllers;

import com.rmmcosta.XMLConfiguration.services.Database;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseController {
    private Database database;
    @Autowired
    public void setDatabase(Database database) {
        this.database = database;
    }

    public void connect() {
        System.out.println(database.open());
    }
}
