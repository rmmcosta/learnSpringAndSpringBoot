package com.rmmcosta.MyCrud.services.security;

public interface EncryptionService {
    String encryptPassword(String plainPassword);

    boolean checkPassword(String plainPassword, String encryptedPassword);
}
