package com.rmmcosta.JavaConfiguration.controllers;

import com.rmmcosta.JavaConfiguration.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DatabaseController {
    private DatabaseService databaseService;
    @Autowired
    public void setDatabaseService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }
    public void connect() {
        System.out.println(databaseService.connect());
    }
}
