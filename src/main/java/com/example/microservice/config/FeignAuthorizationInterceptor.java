package com.example.microservice.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class FeignAuthorizationInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (authenticationToken != null) {
            KeycloakSecurityContext keycloakSecurityContext = (KeycloakSecurityContext) authenticationToken.getCredentials();
            String accessToken = keycloakSecurityContext.getTokenString();
            template.header("Authorization", "Bearer " + accessToken);
        }
    }
}
