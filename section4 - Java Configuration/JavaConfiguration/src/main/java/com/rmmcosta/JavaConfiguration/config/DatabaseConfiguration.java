package com.rmmcosta.JavaConfiguration.config;

import com.rmmcosta.JavaConfiguration.controllers.FactoryController;
import com.rmmcosta.JavaConfiguration.services.DatabaseService;
import com.rmmcosta.JavaConfiguration.services.MySqlDatabase;
import com.rmmcosta.JavaConfiguration.services.OracleDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

@Configuration
public class DatabaseConfiguration {
    @Bean
    @Profile("default")
    public DatabaseService getOracleDatabase() {
        return new OracleDatabase();
    }

    @Bean
    @Profile("mysql")
    public DatabaseService getMySqlDatabase() {
        return new MySqlDatabase();
    }
}
