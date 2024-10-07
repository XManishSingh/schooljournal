package com.singh.journalApp.config;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;



@Configuration

public class SwaggerConfig {
    @Bean
    public OpenAPI myCustomConfig(){
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                .title("Journal School API's")
                .description("By Manish")
                .version("1.0.0"))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("local"),
                        new Server().url("http://localhost:8082").description("live")))
                .tags(Arrays.asList(
                        new Tag().name("Public API's"),
                        new Tag().name("User API's"),
                        new Tag().name("Student API's"),
                        new Tag().name("Subject API's")
                )).components(new io.swagger.v3.oas.models.Components()
                .addSecuritySchemes("bearerAuth", new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .in(SecurityScheme.In.HEADER)
                        .name("Authorization")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
}
