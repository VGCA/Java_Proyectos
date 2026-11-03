package com.sandbox.resilence4j.application.service;

import com.sandbox.resilence4j.application.port.out.ExternalServicePort;
import com.sandbox.resilence4j.domain.exception.ServiceUnavailableException;
import com.sandbox.resilence4j.domain.model.ServiceCallResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Pure unit test: no Spring context, the port is a lambda. */
class CallExternalServiceServiceTest {

    @Test
    void returnsSuccessWhenPortAnswers() {
        ExternalServicePort port = () -> "Service call succeeded";

        ServiceCallResult result = new CallExternalServiceService(port).execute();

        assertFalse(result.fallback());
        assertEquals("Service call succeeded", result.message());
    }

    @Test
    void returnsFallbackWhenPortIsUnavailable() {
        ExternalServicePort port = () -> {
            throw new ServiceUnavailableException("Service failed");
        };

        ServiceCallResult result = new CallExternalServiceService(port).execute();

        assertTrue(result.fallback());
        assertEquals("Service failed", result.message());
    }
}
