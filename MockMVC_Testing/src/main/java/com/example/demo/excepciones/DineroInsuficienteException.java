package com.example.demo.excepciones;

public class DineroInsuficienteException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public DineroInsuficienteException(String mensaje){
        super(mensaje);
    }
}