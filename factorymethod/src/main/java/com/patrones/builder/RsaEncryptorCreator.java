package com.patrones.builder;

public class RsaEncryptorCreator extends EncryptorCreator {
    @Override
    public Encryptor createEncryptor() {
        return new RsaEncryptor();
    }
}
