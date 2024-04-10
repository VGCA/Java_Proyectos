package com.bosonit.CRUDSpring.exceptionConfig;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController)
public class Exception {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex);
    }

    @ExceptionHandler(UnprocesableException.class)
    public ResponseEntity<?> unprocesableException(Exception ex){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex);
    }
}
