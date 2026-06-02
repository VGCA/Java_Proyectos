package com.testing.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CajaExtiende<T extends Dog> {

    private T datos;

    public int howManyLegs() {
        return datos.getLegs();
    }
}
