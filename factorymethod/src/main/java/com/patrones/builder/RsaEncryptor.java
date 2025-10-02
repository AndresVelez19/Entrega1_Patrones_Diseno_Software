package com.patrones.builder;

import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RsaEncryptor implements Encryptor {
    private final Map<String, CryptoEnvelope> storage = new ConcurrentHashMap<>();

    @Override
    public String encrypt(CryptoEnvelope cryptoEnvelopedata) {
        String decryptedkey = Base64.getEncoder().encodeToString((cryptoEnvelopedata.getNumeroTarjeta()).getBytes());
        storage.put(decryptedkey, cryptoEnvelopedata);
        return decryptedkey;
    }

    @Override
    public CryptoEnvelope decrypt(String cardNumber) {
        CryptoEnvelope cryptoEnvelopeData =storage.get(cardNumber);
        final String decryptedCard = new String(Base64.getDecoder().decode(cardNumber));
        return generarCryptoEnvelope(cryptoEnvelopeData, decryptedCard);
    }

    private CryptoEnvelope generarCryptoEnvelope(CryptoEnvelope cryptoEnvelope, String decryptedCard) {
        return  CryptoEnvelope.builder()
                .algoritmo(cryptoEnvelope.getAlgoritmo())
                .version(cryptoEnvelope.getVersion())
                .clave(cryptoEnvelope.getClave())
                .fecha(cryptoEnvelope.getFecha())
                .numeroTarjeta(cryptoEnvelope.getNumeroTarjeta())
                .build();
    }
}
