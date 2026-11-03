package com.sandbox.resilence4j.application.service;

import com.sandbox.resilence4j.application.port.in.CallExternalServiceUseCase;
import com.sandbox.resilence4j.application.port.out.ExternalServicePort;
import com.sandbox.resilence4j.domain.exception.ServiceUnavailableException;
import com.sandbox.resilence4j.domain.model.ServiceCallResult;

/**
 * Use-case implementation. Plain Java: no Spring, no Resilience4j.
 * It owns the *policy* "if the service is unavailable, degrade gracefully".
 */
public class CallExternalServiceService implements CallExternalServiceUseCase {

    private final ExternalServicePort externalService;

    public CallExternalServiceService(ExternalServicePort externalService) {
        this.externalService = externalService;
    }

    @Override
    public ServiceCallResult execute() {
        try {
            return ServiceCallResult.success(externalService.call());
        } catch (ServiceUnavailableException ex) {
            return ServiceCallResult.fallback(ex.getMessage());
        }
    }
}
