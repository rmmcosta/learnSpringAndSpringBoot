package com.rmmcosta.JavaConfiguration.services;

public class MySqlFactory implements DatabaseFactory {
    @Override
    public DatabaseService createDatabase() {
        return new MySqlDatabase();
    }
}
