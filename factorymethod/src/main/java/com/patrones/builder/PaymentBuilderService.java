package com.patrones.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentBuilderService {
    @Autowired
    @Qualifier("builderEncryptor")
    private final Encryptor encryptor;

    public PaymentBuilderService(Encryptor encryptor) {
        this.encryptor = encryptor;
    }

    public String guardarTarjeta(CryptoEnvelope cryptoEnvelope) {
        return encryptor.encrypt(cryptoEnvelope);
    }

    public CryptoEnvelope leerTarjeta(String cardNumber) {
        return encryptor.decrypt(cardNumber);
    }

}
