package com.portfolio.ecommerce.infrastructure.adapter.in.web;

import com.portfolio.ecommerce.domain.exception.EmailYaRegistradoException;
import com.portfolio.ecommerce.domain.exception.RecursoNoEncontradoException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/** Traduce excepciones de dominio a códigos HTTP (el dominio no sabe de HTTP). */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public void noEncontrado(RecursoNoEncontradoException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(EmailYaRegistradoException.class)
    public void emailDuplicado(EmailYaRegistradoException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_CONFLICT, e.getMessage());
    }
}
