package com.bosonit.crudspring.exceptionConfig;

import java.sql.Timestamp;

public class UnprocesableException extends RuntimeException{

    private int codigo;
    private Timestamp tiempo;

    public UnprocesableException(){}

    public UnprocesableException(String mensaje, int codigo, Timestamp tiempo){
        super(mensaje);
        this.codigo=codigo;
        this.tiempo=tiempo;
    }
}
