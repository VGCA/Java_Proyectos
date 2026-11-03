package com.sandbox.resilence4j.domain.model;

/**
 * Outcome of calling the external service.
 *
 * @param message  payload (success message) or failure reason
 * @param fallback true when the message is a degraded answer, not a real one
 */
public record ServiceCallResult(String message, boolean fallback) {

    public static ServiceCallResult success(String message) {
        return new ServiceCallResult(message, false);
    }

    public static ServiceCallResult fallback(String reason) {
        return new ServiceCallResult(reason, true);
    }
}
