package com.patrones.factorymethod;

import java.util.Base64;

public class RsaEncryptor implements Encryptor {
    @Override
    public String encrypt(String data) {
        // Simulación de cifrado RSA
        return Base64.getEncoder().encodeToString(("RSA-" + data).getBytes());
    }

    @Override
    public String decrypt(String data) {
        String decoded = new String(Base64.getDecoder().decode(data));
        return decoded.replace("RSA-", "");
    }
}
