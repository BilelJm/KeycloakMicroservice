package com.example.microservice;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FeignClientConfig implements RequestInterceptor {

    private static final String JWT_TOKEN_TYPE = "Bearer";

    @Override
    public void apply(RequestTemplate template) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Optional.ofNullable(securityContext.getAuthentication())
                .filter(auth -> auth.getCredentials() instanceof Jwt)
                .map(auth -> (Jwt) auth.getCredentials())
                .map(AbstractOAuth2Token::getTokenValue)
                .ifPresent(tokenValue -> template.header(HttpHeaders.AUTHORIZATION, String.format("%s %s", JWT_TOKEN_TYPE, tokenValue)));
    }

}
