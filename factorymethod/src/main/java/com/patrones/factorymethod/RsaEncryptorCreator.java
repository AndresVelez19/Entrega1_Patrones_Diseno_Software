package com.patrones.factorymethod;

public class RsaEncryptorCreator extends EncryptorCreator {
    @Override
    public Encryptor createEncryptor() {
        return new RsaEncryptor();
    }
}
