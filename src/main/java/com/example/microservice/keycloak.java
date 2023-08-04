package com.example.microservice;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient( value = "jplaceholder", url = "localhost:8081/api/v1/demo")
public interface keycloak {

    @GetMapping("/keycloakAdmin")
    String keycloakAdmin();

    @GetMapping("/keycloakUser")
    String keycloakUser();
}
