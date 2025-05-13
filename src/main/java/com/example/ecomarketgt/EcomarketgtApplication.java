package com.example.ecomarketgt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.ecomarketgt") // Escanea el paquete base
public class EcomarketgtApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcomarketgtApplication.class, args);
    }
}
