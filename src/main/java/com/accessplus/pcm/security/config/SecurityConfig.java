package com.accessplus.pcm.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import reactor.core.publisher.Mono;

/**
 * @author Anish Panthi
 */
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    return http.csrf()
        .disable()
        .authorizeExchange()
        //        .pathMatchers(
        //            "/login",
        //            "/register",
        //            "/admin/**",
        //            "/v2/api-docs",
        //            "/configuration/**",
        //            "/swagger-ui/**",
        //            "/swagger-ui.html",
        //            "/webjars/swagger-ui/index.html",
        //            "/webjars*/**")
        .anyExchange()
        .permitAll()
        .anyExchange()
        .authenticated()
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(
            (swe, e) ->
                Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED)))
        .accessDeniedHandler(
            (swe, e) ->
                Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN)))
        .and()
        .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
        .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
