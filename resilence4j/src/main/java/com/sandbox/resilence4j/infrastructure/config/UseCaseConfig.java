package com.sandbox.resilence4j.infrastructure.config;

import com.sandbox.resilence4j.application.port.in.CallExternalServiceUseCase;
import com.sandbox.resilence4j.application.port.out.ExternalServicePort;
import com.sandbox.resilence4j.application.service.CallExternalServiceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Composition root: the only place that wires the framework-free
 * application layer into Spring, so the core needs no @Service annotations.
 */
@Configuration
public class UseCaseConfig {

    @Bean
    CallExternalServiceUseCase callExternalServiceUseCase(ExternalServicePort externalServicePort) {
        return new CallExternalServiceService(externalServicePort);
    }
}
