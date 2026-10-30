package com.pruebatecnica.prueba.tecnica.senior.domain.exception;

/** Business error: the requested search does not exist. */
public class SearchNotFoundException extends RuntimeException {

    public SearchNotFoundException() {
        super("Search not found");
    }
}
