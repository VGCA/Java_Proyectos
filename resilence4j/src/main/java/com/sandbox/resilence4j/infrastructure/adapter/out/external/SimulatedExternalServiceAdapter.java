package com.sandbox.resilence4j.infrastructure.adapter.out.external;

import com.sandbox.resilence4j.application.port.out.ExternalServicePort;
import com.sandbox.resilence4j.domain.exception.ServiceUnavailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Component;

/**
 * Outbound adapter. Resilience4j lives here because the circuit breaker is a
 * technical concern about *how we talk to* a remote system.
 */
@Component
public class SimulatedExternalServiceAdapter implements ExternalServicePort {

    private static final String SAMPLE_SERVICE = "sampleService";

    @Override
    @CircuitBreaker(name = SAMPLE_SERVICE, fallbackMethod = "translateFailure")
    public String call() {
        if (Math.random() > 0.5) {
            throw new ServiceUnavailableException("Service failed");
        }
        return "Service call succeeded";
    }

    /**
     * Resilience4j fallback. It catches both real failures and
     * CallNotPermittedException (breaker OPEN) and translates them into the
     * single exception the application layer understands.
     */
    public String translateFailure(Exception ex) {
        throw new ServiceUnavailableException(ex.getMessage());
    }
}
