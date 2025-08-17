package com.bosonit.jwtsecurity.exceptions;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
}
