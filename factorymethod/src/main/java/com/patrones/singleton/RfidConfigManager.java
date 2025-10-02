package com.patrones.singleton;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "rfid")
public class RfidConfigManager {

    private String active;
    private Map<String, RfidConfig> configs;

    public RfidConfig getActiveConfig() {
        if (active == null || !configs.containsKey(active)) {
            throw new IllegalStateException("Configuración RFID no válida: " + active);
        }
        return configs.get(active);
    }

    @Data
    public static class RfidConfig {
        private String protocolo;
        private String antena;
        private String potencia;
        private String algoritmoFiltrado;
    }
}