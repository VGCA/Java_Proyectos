package com.portfolio.ecommerce.domain.exception;

public class EmailYaRegistradoException extends RuntimeException {

    public EmailYaRegistradoException(String email) {
        super("El email ya está registrado: " + email);
    }
}
