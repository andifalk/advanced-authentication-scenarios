package com.example.gateway;

import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.BearerTokenError;
import org.springframework.security.oauth2.server.resource.BearerTokenErrorCodes;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class BearerTokenFromCookieAuthenticationConverter implements ServerAuthenticationConverter {
  private static final String TOKEN_COOKIE = "SESSION";

  public Mono<Authentication> convert(ServerWebExchange exchange) {
    return Mono.justOrEmpty(token(exchange.getRequest()))
        .map(
            token -> {
              if (token.isEmpty()) {
                BearerTokenError error = invalidTokenError();
                throw new OAuth2AuthenticationException(error);
              }
              return new BearerTokenAuthenticationToken(token);
            });
  }

  private String token(ServerHttpRequest request) {
    return resolveFromCookie(request.getCookies());
  }

  private static String resolveFromCookie(MultiValueMap<String, HttpCookie> cookies) {
    HttpCookie cookie = cookies.getFirst(TOKEN_COOKIE);
    if (cookie != null) {
      return cookie.getValue();
    }
    return null;
  }

  private static BearerTokenError invalidTokenError() {
    return new BearerTokenError(
        BearerTokenErrorCodes.INVALID_TOKEN,
        HttpStatus.UNAUTHORIZED,
        "Bearer token is malformed",
        "https://tools.ietf.org/html/rfc6750#section-3.1");
  }
}
