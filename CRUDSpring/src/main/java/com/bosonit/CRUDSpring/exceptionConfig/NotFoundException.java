package com.bosonit.CRUDSpring.exceptionConfig;

import java.sql.Timestamp;

public class NotFoundException extends RuntimeException{

    private int codigo;
    private Timestamp tiempo;

    public NotFoundException(){}

    public NotFoundException(String mensaje, int codigo, Timestamp tiempo){
        super(mensaje);
        this.codigo=codigo;
        this.tiempo=tiempo;
    }
}
