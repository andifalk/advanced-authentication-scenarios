package com.example.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class WebSecurityConfiguration {

  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
    http.authorizeExchange(exchanges -> exchanges.anyExchange().authenticated())
        .csrf()
        .disable()
        .oauth2ResourceServer()
        .bearerTokenConverter(new BearerTokenFromCookieAuthenticationConverter())
        .jwt();
    return http.build();
  }
}
