package com.example.samesitecookie.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class SamesiteCookieServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SamesiteCookieServerApplication.class, args);
  }
}
