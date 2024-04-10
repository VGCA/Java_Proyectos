package com.bosonit.gestionusuarios.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecursoNoEncontrado extends RuntimeException {

    private static final long serialID = 1L;

    public RecursoNoEncontrado(String mensaje) {
        super(mensaje);
    }

}
