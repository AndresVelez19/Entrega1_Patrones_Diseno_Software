package com.patrones.builder;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import java.time.LocalDateTime;

@Value
@Builder
public class CryptoEnvelope {
    @NonNull String algoritmo;
    @NonNull String version;
    @NonNull String clave;
    @NonNull LocalDateTime fecha;
    @NonNull String numeroTarjeta;
}
