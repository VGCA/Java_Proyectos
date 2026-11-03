package com.sandbox.resilence4j.infrastructure.adapter.in.web;

import com.sandbox.resilence4j.application.port.in.CallExternalServiceUseCase;
import com.sandbox.resilence4j.domain.model.ServiceCallResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** Inbound (driving) adapter: translates HTTP into a use-case call and back. */
@RestController
public class CircuitBreakerController {

    private static final String FALLBACK_PREFIX = "Fallback response: ";

    private final CallExternalServiceUseCase callExternalService;

    public CircuitBreakerController(CallExternalServiceUseCase callExternalService) {
        this.callExternalService = callExternalService;
    }

    @GetMapping("/test")
    public String testCircuitBreaker() {
        ServiceCallResult result = callExternalService.execute();
        return result.fallback() ? FALLBACK_PREFIX + result.message() : result.message();
    }
}
