package com.bosonit.zipkincliente.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

@RestController
public class WebController {

    @Lazy //Sin esto no funcionaba y me daba error "form cycle"
    @Autowired 
    public RestTemplate restTemplate;
    
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