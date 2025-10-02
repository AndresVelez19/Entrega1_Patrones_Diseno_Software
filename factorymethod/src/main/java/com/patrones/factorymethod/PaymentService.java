package com.patrones.factorymethod;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final Encryptor encryptor;

    public PaymentService(Encryptor encryptor) {
        this.encryptor = encryptor;
    }

    public String guardarTarjeta(String numeroTarjeta) {
        return encryptor.encrypt(numeroTarjeta);
    }

    public String leerTarjeta(String dataCifrada) {
        return encryptor.decrypt(dataCifrada);
    }

}
