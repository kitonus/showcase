package com.jatis.showcase.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SpringdocConfig {
	@Bean
	OpenAPI customOpenAPI(
			@Value("${spring.application.name:'Service API'}") String appName,
			@Value("${server.servlet.context-path:/}") String contextPath) {
	    return new OpenAPI()
	    		.addServersItem(new Server().url(contextPath).description("Default"))
	    		
                .components(new Components()
                .addSecuritySchemes("Bearer token", new SecurityScheme()
                		.name("Bearer token").type(SecurityScheme.Type.HTTP).scheme("bearer")
                		.bearerFormat("JWT"))    		
	    		)
                .info(new Info().title(appName).version("".equals(contextPath) ? "v1" : contextPath));
	}
	
}
