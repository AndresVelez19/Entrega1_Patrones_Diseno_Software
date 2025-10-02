package com.patrones.builder;

public class AesEncryptorCreator extends EncryptorCreator {
    @Override
    public Encryptor createEncryptor() {
        return new AesEncryptor();
    }
}
