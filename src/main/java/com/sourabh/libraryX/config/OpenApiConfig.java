package com.sourabh.libraryX.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI libraryXOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("LibraryX API")
                        .description("Enterprise Library Management Microservice — RESTful API for managing books, inventory, and catalog operations.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Sourabh")
                                .email("sourabh@example.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server().url("http://localhost:6782").description("Local Development Server")
                ));
    }
}
