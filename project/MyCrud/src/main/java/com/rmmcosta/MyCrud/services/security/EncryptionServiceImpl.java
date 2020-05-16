package com.rmmcosta.MyCrud.services.security;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements EncryptionService {
    private StrongPasswordEncryptor passwordEncryptor;

    @Autowired
    public void setPasswordEncryptor(StrongPasswordEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }

    @Override
    public String encryptPassword(String plainPassword) {
        return passwordEncryptor.encryptPassword(plainPassword);
    }

    @Override
    public boolean checkPassword(String plainPassword, String encryptedPassword) {
        return passwordEncryptor.checkPassword(plainPassword, encryptedPassword);
    }
}
