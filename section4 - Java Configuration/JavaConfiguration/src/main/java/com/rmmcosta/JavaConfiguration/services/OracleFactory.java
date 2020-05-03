package com.rmmcosta.JavaConfiguration.services;

public class OracleFactory implements DatabaseFactory {
    @Override
    public DatabaseService createDatabase() {
        return new OracleDatabase();
    }
}
