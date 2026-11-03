package com.sandbox.resilence4j.infrastructure.adapter.out.external;

import com.sandbox.resilence4j.domain.exception.ServiceUnavailableException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimulatedExternalServiceAdapterTest {

    private final SimulatedExternalServiceAdapter adapter = new SimulatedExternalServiceAdapter();

    @Test
    void callEitherSucceedsOrThrowsDomainException() {
        for (int i = 0; i < 100; i++) {
            try {
                assertEquals("Service call succeeded", adapter.call());
            } catch (ServiceUnavailableException ex) {
                assertEquals("Service failed", ex.getMessage());
            }
        }
    }

    @Test
    void fallbackTranslatesAnyFailureIntoDomainException() {
        ServiceUnavailableException ex = assertThrows(ServiceUnavailableException.class,
                () -> adapter.translateFailure(new IllegalStateException("breaker is OPEN")));

        assertTrue(ex.getMessage().contains("breaker is OPEN"));
    }
}
