package com.curso.infraestructura.adaptador.entrada.rest;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.curso.dominio.excepcion.RecursoNoEncontradoException;

@RestControllerAdvice(basePackageClasses = PedidosRest.class)
public class ManejadorErroresRest {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errores = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(fe -> fe.getField(),
                                          fe -> String.valueOf(fe.getDefaultMessage()),
                                          (a, b) -> a + "; " + b));
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<String> handleNoEncontrado(RecursoNoEncontradoException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
