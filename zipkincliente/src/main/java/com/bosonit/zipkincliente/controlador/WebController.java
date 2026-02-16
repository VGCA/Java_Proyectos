package com.bosonit.zipkincliente.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

@RestController
public class WebController {

    private RestTemplate restTemplate;

    public WebController(@Lazy RestTemplate service) { // Add @Lazy here
        this.restTemplate = service;
    }

    @GetMapping("/")
    public String helloWorld() {
        String response = restTemplate.getForObject("http://localhost:8080/saludo", String.class);
        return "<h1>Respuesta desde " + response + "</h1>";
    }
 
    @Bean 
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}