package com.patrones.factorymethod;

import java.util.Base64;

public class AesEncryptor implements Encryptor {
    @Override
    public String encrypt(String cardNumber) {
        // Simulación de cifrado AES
        return Base64.getEncoder().encodeToString(("AES-" + cardNumber).getBytes());
    }

    @Override
    public String decrypt(String cardNumber) {
        String decoded = new String(Base64.getDecoder().decode(cardNumber));
        return decoded.replace("AES-", "");
    }
}
