package com.patrones.factorymethod;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptorFactoryConfig {

    @Value("${app.encryptor.active}") // configurable en application.properties
    private String encryptorType;

    @Bean
    public EncryptorCreator encryptorCreator() {
        switch (encryptorType.toUpperCase()) {
            case "RSA":
                return new RsaEncryptorCreator();
            case "AES":
                return new AesEncryptorCreator();
            default:
                throw new IllegalArgumentException("Tipo de cifrado no soportado: " + encryptorType);
        }
    }

    @Bean
    public Encryptor encryptor(EncryptorCreator creator) {
        return creator.createEncryptor();
    }
}
