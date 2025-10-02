package com.patrones.builder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptorBuilderConfig {

    @Value("${app.encryptor.active}") // configurable en application.properties
    private String encryptorType;

    @Bean
    public EncryptorCreator encryptorCreatorBuilder() {
        switch (encryptorType.toUpperCase()) {
            case "RSA":
                return new RsaEncryptorCreator();
            case "AES":
                return new AesEncryptorCreator();
            default:
                throw new IllegalArgumentException("Tipo de cifrado no soportado: " + encryptorType);
        }
    }

    @Bean(name = "builderEncryptor")
    public Encryptor encryptorBuilder(EncryptorCreator creator) {
        return creator.createEncryptor();
    }
}
