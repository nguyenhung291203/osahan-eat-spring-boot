package org.example.osahaneatspringboot.configuration;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info =
                @Info(
                        title = "E-commerce api in Java Spring boot",
                        version = "1.0.0",
                        description = "Ứng dụng đặt đồ ăn"),
        servers = {
            @Server(url = "http://localhost:8080", description = "Local Development Server"),
            @Server(url = "http://192.168.0.103:8080", description = "Production Server"),
        })
@SecurityScheme(
        name = "bearer-key", // Can be any name, used to reference this scheme in the @SecurityRequirement annotation
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER)
@Configuration
public class OpenApiConfig {}
