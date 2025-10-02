package com.patrones;

import com.patrones.builder.CryptoEnvelope;
import com.patrones.builder.EncryptorProperties;
import com.patrones.builder.PaymentBuilderService;
import com.patrones.factorymethod.PaymentService;
import com.patrones.singleton.RfidConfigManager;
import com.patrones.singleton.RfidReaderConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDateTime;

@SpringBootApplication
public class PatronesApplication {
	private final PaymentService paymentService;
    private final PaymentBuilderService paymentBuilderService;
    private final EncryptorProperties encryptorProperties;
    private final RfidConfigManager rfidConfigManager;

	public PatronesApplication(PaymentService paymentService, PaymentBuilderService paymentBuilderService, EncryptorProperties encryptorProperties, RfidConfigManager rfidConfigManager) {
		this.paymentService = paymentService;
        this.paymentBuilderService = paymentBuilderService;
        this.encryptorProperties = encryptorProperties;
        this.rfidConfigManager = rfidConfigManager;
    }

	public static void main(String[] args) {
		SpringApplication.run(PatronesApplication.class, args);
	}


    @EventListener(ApplicationReadyEvent.class)
    private void inicializar() {
        pruebaPatronFactoryMethod();
        pruebaPatronBuilder();
        pruebaPatronSingleton();
    }

	private void pruebaPatronFactoryMethod() {
        try {
            String tarjeta = "4111111111111111";
            String cifrada = paymentService.guardarTarjeta(tarjeta);
            String descifrada = paymentService.leerTarjeta(cifrada);

            System.out.println("\n ******* Inicio Patrón de Factory Method. ******* \n");
            System.out.println("Tarjeta original: " + tarjeta);
            System.out.println("Cifrada: " + cifrada);
            System.out.println("Descifrada: " + descifrada);
            System.out.println("\n ******* Fin Patrón de Factory Method. ********* \n\n");
        }
        catch (Exception e) {
           System.out.println("Exception pruebaPatronFactoryMethod: " + e.getMessage());
        }
	}



    private void pruebaPatronBuilder()  {
        try {
        String tarjeta = "4666333366333633";

        String cifrada = paymentBuilderService.guardarTarjeta(generarCryptoEnvelope(tarjeta));
        CryptoEnvelope cryptoEnvelope = paymentBuilderService.leerTarjeta(cifrada);

        System.out.println("\n ******* Inicio Patrón de Builder ******* \n");
        System.out.println("Tarjeta original: " + tarjeta);
        System.out.println("Tarjeta Cifrada: " + cifrada);
        System.out.println("Busqueda datos tarjeta Cifrada: " + cryptoEnvelope.toString());
        System.out.println("\n ******* Fin Patrón de Builder ********* \n\n");

        } catch (Exception e) {
            System.out.println("Exception pruebaPatronBuilder: " + e.getMessage());
        }

    }

    private void pruebaPatronSingleton()  {
        try {
            RfidConfigManager.RfidConfig config = rfidConfigManager.getActiveConfig();
            RfidReaderConfig rfidReaderConfig=consultRfidConfigManager(config);

            System.out.println("\n ******* Inicio Patrón de Singleton ******* \n");
            System.out.println("Configuración Lector RFID: " + rfidReaderConfig.toString());
            System.out.println("\n ******* Fin Patrón de Singleton ********* \n\n");

        } catch (Exception e) {
            System.out.println("Exception pruebaPatronSingleton: " + e.getMessage());
        }
    }
    private RfidReaderConfig consultRfidConfigManager(RfidConfigManager.RfidConfig config) {
        return  RfidReaderConfig.builder()
                .protocolo(config.getProtocolo())
                .antena(config.getAntena())
                .potencia(config.getPotencia())
                .algoritmoFiltrado(config.getAlgoritmoFiltrado())
                .build();
    }

    private CryptoEnvelope generarCryptoEnvelope(String numeroTarjeta) {
        EncryptorProperties.EncryptorConfig config = encryptorProperties.getActiveConfig();
        return  CryptoEnvelope.builder()
                .algoritmo(config.getAlgoritmo())
                .version(config.getVersion())
                .clave(config.getClave())
                .fecha(LocalDateTime.now())
                .numeroTarjeta(numeroTarjeta)
                .build();
    }
}
