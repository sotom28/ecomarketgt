package com.example.ecomarketgt.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("EcoMarket GT API")
                        .version("1.0.0")
                        .description("API REST para la gestión de usuarios y perfiles del sistema EcoMarket GT")
                        .contact(new Contact()
                                .name("EcoMarket GT Team")
                                .email("admin@ecomarketgt.com")));
    }
}
