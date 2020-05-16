package com.rmmcosta.MyCrud.config;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public StrongPasswordEncryptor getStrongPasswordEncryptor() {
        return new StrongPasswordEncryptor();
    }
}
