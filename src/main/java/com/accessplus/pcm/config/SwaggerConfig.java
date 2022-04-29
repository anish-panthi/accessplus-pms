package com.accessplus.pcm.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author - Anish Panthi
 */
@Profile("!prod")
@Configuration
@OpenAPIDefinition(
    info =
        @Info(
            title = "PMS Reactive Service",
            version = "1.0",
            description = "AccessPlus PMS Reactive Service"))
@SecurityScheme(
    name = "accessplus-pms",
    scheme = "Bearer",
    type = SecuritySchemeType.HTTP,
    in = SecuritySchemeIn.HEADER)
public class SwaggerConfig {}
