package com.example.demo;

import com.example.demo.entidades.Banco;
import com.example.demo.entidades.Cuenta;

import java.math.BigDecimal;
import java.util.Optional;

public class Datos {

    private Datos() {
    }

    public static Optional<Cuenta> crearCuenta001(){
        return Optional.of(new Cuenta(1L,"Mario",new BigDecimal("1000")));
    }

    public static Optional<Cuenta> crearCuenta002(){
        return Optional.of(new Cuenta(2L,"Julián",new BigDecimal("2000")));
    }

    public static Optional<Banco> crearBanco(){
        return Optional.of(new Banco(1L,"El banco financiero",0));
    }
}
