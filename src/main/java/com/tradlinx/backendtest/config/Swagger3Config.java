package com.tradlinx.backendtest.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class Swagger3Config {
	@Value("${jwt.secret}")
	private String secret;
	
	  @Bean
	  public OpenAPI openAPI(@Value("${springdoc.version}") String springdocVersion) {
	    Info info = new Info()
	        .title("트래드링스 테스트")
	        .version(springdocVersion)
	        .description("Using Swagger3 API");

	    return new OpenAPI()
	            .components(
	                    new Components()
	                            .addSecuritySchemes(secret,
	                                    new SecurityScheme()
	                                            .type(SecurityScheme.Type.HTTP)
	                                            .scheme("bearer")
	                                            .bearerFormat("JWT")
	                            )
	            )
	            .security(List.of(new SecurityRequirement().addList(secret)))
	            .info(info);
	  }
}
