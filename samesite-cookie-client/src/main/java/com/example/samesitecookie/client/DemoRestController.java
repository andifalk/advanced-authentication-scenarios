package com.example.samesitecookie.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class DemoRestController {

  private final WebClient webClient;

  public DemoRestController(WebClient webClient) {
    this.webClient = webClient;
  }

  @GetMapping("/test")
  public Mono<String> hello1() {

    return Mono.just("<html><body><img src=\"http://test1.local:9090\"><img></body></html>");

    //return webClient.get().uri("http://test1.local:9090").retrieve().bodyToMono(String.class);
  }

}
