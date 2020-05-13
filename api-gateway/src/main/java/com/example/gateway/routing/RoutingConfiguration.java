package com.example.gateway.routing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.security.oauth2.gateway.TokenRelayGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutingConfiguration {

  private final TokenRelayGatewayFilterFactory filterFactory;

  @Autowired
  public RoutingConfiguration(TokenRelayGatewayFilterFactory filterFactory) {
    this.filterFactory = filterFactory;
  }

  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    return builder.routes()
            .route("/", r -> r.path("/")
                    .filters(f -> f.filter(filterFactory.apply()))
                    .uri("http://localhost:9096"))
            .route("/api1", r -> r.path("/api1")
                    //.filters(f -> f.filter(filterFactory.apply()))
                    .uri("http://localhost:9095/api1"))
            .route("/api2", r -> r.path("/api2")
                    //.filters(f -> f.filter(filterFactory.apply()))
                    .uri("http://localhost:9095"))
            .build();
  }

}
