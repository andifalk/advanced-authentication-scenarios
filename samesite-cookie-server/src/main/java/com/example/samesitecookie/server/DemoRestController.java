package com.example.samesitecookie.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class DemoRestController {

  @GetMapping("/")
  public Mono<String> hello() {
    return Mono.just("hello from server");
  }

}
