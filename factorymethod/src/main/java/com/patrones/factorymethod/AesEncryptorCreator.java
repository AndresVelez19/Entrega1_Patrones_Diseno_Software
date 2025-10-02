package com.patrones.factorymethod;

public class AesEncryptorCreator extends EncryptorCreator{
    @Override
    public Encryptor createEncryptor() {
        return new AesEncryptor();
    }
}
