package com.sandbox.resilence4j.service;

import com.sandbox.resilence4j.exceptions.ServiceUnavailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    private static final String SAMPLE_SERVICE = "sampleService";

    @CircuitBreaker(name = SAMPLE_SERVICE, fallbackMethod = "fallbackResponse")
    public String callExternalService() {

        if (Math.random() > 0.5) {
            throw new ServiceUnavailableException("Service failed");
        }
        return "Service call succeeded";
    }
    public String fallbackResponse(Exception ex) {
        return "Fallback response: " + ex.getMessage();
    }
}
