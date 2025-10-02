package com.patrones.singleton;


import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class RfidReaderConfig {
    @NonNull String protocolo;
    @NonNull String antena;
    @NonNull String potencia;
    @NonNull String algoritmoFiltrado;
}

