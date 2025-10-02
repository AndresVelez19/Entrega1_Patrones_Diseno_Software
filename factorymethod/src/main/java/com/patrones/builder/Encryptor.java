package com.patrones.builder;

public interface Encryptor {
    String encrypt(CryptoEnvelope cryptoEnvelope);
    CryptoEnvelope decrypt(String cardNumber);
}
