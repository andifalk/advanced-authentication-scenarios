package com.example.samesitecookie.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
public class DemoController {

  @GetMapping("/")
  public Mono<String> hello() {

    return Mono.just("redirect:http://test1.local:9090");
  }
}
