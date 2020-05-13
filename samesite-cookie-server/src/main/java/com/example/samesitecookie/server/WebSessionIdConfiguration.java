package com.example.samesitecookie.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.server.session.CookieWebSessionIdResolver;
import org.springframework.web.server.session.DefaultWebSessionManager;
import org.springframework.web.server.session.WebSessionIdResolver;
import org.springframework.web.server.session.WebSessionManager;

@Configuration
public class WebSessionIdConfiguration {

  @Value("${samesite.cookie}")
  private String sameSiteCookie;

  @Primary
  @Bean
  public WebSessionIdResolver webSessionIdResolver() {
    CookieWebSessionIdResolver customResolver = new CookieWebSessionIdResolver();
    customResolver.setCookieName("SESSION");
    customResolver.addCookieInitializer(builder -> builder.path("/"));
    customResolver.addCookieInitializer(builder -> builder.sameSite(sameSiteCookie));
    return customResolver;
  }

  @Bean
  public WebSessionManager webSessionManager() {
    DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
    defaultWebSessionManager.setSessionIdResolver(webSessionIdResolver());
    return defaultWebSessionManager;
  }

}
