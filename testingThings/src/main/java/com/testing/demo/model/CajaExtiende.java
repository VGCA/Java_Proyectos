package com.testing.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CajaExtiende<Integer extends Dog>{

    private Integer datos;

    public int howManyLegs(){
        return datos.getLegs();
    }
}
