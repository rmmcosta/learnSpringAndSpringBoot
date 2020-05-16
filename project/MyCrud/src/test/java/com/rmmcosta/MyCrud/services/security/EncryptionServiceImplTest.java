package com.rmmcosta.MyCrud.services.security;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EncryptionServiceImplTest {
    private StrongPasswordEncryptor passwordEncryptor;

    @Autowired
    public void setPasswordEncryptor(StrongPasswordEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }

    @Test
    void encryptPassword() {
        String password = "123456";
        String encryptPassword = passwordEncryptor.encryptPassword(password);
        assertTrue(passwordEncryptor.checkPassword(password, encryptPassword));
    }

    @Test
    void checkPassword() {
        String password = "123456";
        String encryptPassword = passwordEncryptor.encryptPassword(password);
        assertTrue(passwordEncryptor.checkPassword(password, encryptPassword));
    }
}