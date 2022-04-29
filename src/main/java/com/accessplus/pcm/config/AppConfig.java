package com.accessplus.pcm.config;

import com.accessplus.pcm.util.WebClientFilterUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.resolver.DefaultAddressResolverGroup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;
import org.springframework.web.server.WebFilter;
import reactor.netty.http.client.HttpClient;

/**
 * @author Anish Panthi
 */
@Configuration
@EnableWebFlux
public class AppConfig implements WebFluxConfigurer {

  @Value("${spring.webflux.base-path}")
  private String contextPath;

  @Bean
  public WebClient webClient() {
    var httpClient =
        HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE).followRedirect(true);
    return WebClient.builder()
        .clientConnector(new ReactorClientHttpConnector(httpClient))
        .filter(WebClientFilterUtil.logRequest())
        .filter(WebClientFilterUtil.logResponse())
        .build();
  }

  @Bean
  @Primary
  public WebFluxProperties webFluxProperties() {
    return new WebFluxProperties();
  }

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  public WebFilter contextPathWebFilter() {
    return (exchange, chain) -> {
      ServerHttpRequest request = exchange.getRequest();
      if (request.getURI().getPath().startsWith(contextPath)) {
        return chain.filter(
            exchange.mutate().request(request.mutate().contextPath(contextPath).build()).build());
      }
      return chain.filter(exchange);
    };
  }

  // To enable Pageable/Sort
  @Override
  public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
    configurer.addCustomResolver(new ReactivePageableHandlerMethodArgumentResolver());
  }
}
