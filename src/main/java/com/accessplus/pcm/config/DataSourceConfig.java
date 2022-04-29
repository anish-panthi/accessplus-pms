package com.accessplus.pcm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/**
 * @author Anish Panthi
 */
@Configuration
@EnableR2dbcRepositories(basePackages = {"com.accessplus.pcm.domain",
    "com.accessplus.pcm.security.domain"})
public class DataSourceConfig {

}
