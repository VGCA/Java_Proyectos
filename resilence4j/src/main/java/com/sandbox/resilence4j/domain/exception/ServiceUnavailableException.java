package com.sandbox.resilence4j.domain.exception;

/**
 * Domain-level failure: "the thing we depend on cannot serve us right now".
 * It deliberately knows nothing about Resilience4j, HTTP or Spring.
 */
public class ServiceUnavailableException extends RuntimeException {

    public ServiceUnavailableException(String message) {
        super(message);
    }

}
