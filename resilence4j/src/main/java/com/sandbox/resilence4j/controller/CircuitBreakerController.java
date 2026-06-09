package com.sandbox.resilence4j.controller;

import com.sandbox.resilence4j.service.SampleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerController {

    private final SampleService sampleService;

    public CircuitBreakerController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping("/test")
    public String testCircuitBreaker() {
        return sampleService.callExternalService();
    }
}
