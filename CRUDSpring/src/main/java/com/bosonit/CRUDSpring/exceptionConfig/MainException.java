package com.bosonit.crudspring.exceptionConfig;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MainException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Exception> notFoundException(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex);
    }

    @ExceptionHandler(UnprocesableException.class)
    public ResponseEntity<Exception> unprocesableException(Exception ex){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex);
    }
}
