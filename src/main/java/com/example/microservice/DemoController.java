package com.example.microservice;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    private final keycloak keycloak;

    public DemoController(com.example.microservice.keycloak keycloak) {
        this.keycloak = keycloak;
    }

    @GetMapping("/microserviceUser")
    @PreAuthorize("hasRole('user')")
    public String microserviceUser() {
        return "Microservice - USER" + keycloak.keycloakUser();
    }

    @GetMapping("/microserviceAdmin")
    @PreAuthorize("hasRole('admin')")
    public String microserviceAdmin() {
        return "Microservice - ADMIN" + keycloak.keycloakAdmin();
    }

    @GetMapping("/microserviceAdminUser")
    @PreAuthorize("hasRole('admin')")
    public String microserviceAdminUser() {
        return "Microservice - ADMIN" + keycloak.keycloakUser();
    }
}