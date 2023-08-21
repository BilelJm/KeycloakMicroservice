package com.example.microservice;

import com.example.microservice.config.KeycloakClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1/demo")
public class DemoController {

    private final KeycloakClient keycloakClient;

    public DemoController(KeycloakClient keycloakClient) {
        this.keycloakClient = keycloakClient;
    }

    @GetMapping("/microserviceUser")
    @PreAuthorize("hasAuthority('USER')")
    public String microserviceUser(Principal principal) {
        return "Microservice - USER" + keycloakClient.getKeycloakUser();
    }

    @GetMapping(value = "/microserviceAdmin")
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public String microserviceAdmin() {
        return "Microservice ADMIN + " + keycloakClient.getKeycloakAdmin();
    }

    @GetMapping("/microserviceAdminUser")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public String microserviceAdminUser() {
        return "Microservice - ADMIN" + keycloakClient.getKeycloakUser() ;
    }
}
