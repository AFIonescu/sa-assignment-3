package com.library.bookmanagement.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI/Swagger Configuration
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI bookManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Book Management System API")
                        .description("Spring Boot REST API demonstrating Facade and Decorator design patterns")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Library Management Team")
                                .email("library@example.com")));
    }
}
