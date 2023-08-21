package com.example.microservice.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "keycloak", url = "http://localhost:8086/api/v1/keycloak")
public interface KeycloakClient {

    @GetMapping("/keycloakAdmin")
    String getKeycloakAdmin();

    @GetMapping("/keycloakUser")
    String getKeycloakUser();
}
