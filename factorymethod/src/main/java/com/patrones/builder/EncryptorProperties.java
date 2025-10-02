package com.patrones.builder;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "app.encryptor")
public class EncryptorProperties {

    private String active; // AES o RSA
    private Map<String, EncryptorConfig> configs;

    public EncryptorConfig getActiveConfig() {
        if (active == null || !configs.containsKey(active)) {
            throw new IllegalStateException("Tipo de cifrado no válido: " + active);
        }
        return configs.get(active);
    }

    @Data
    public static class EncryptorConfig {
        private String algoritmo;
        private String clave;
        private String version;
    }
}

