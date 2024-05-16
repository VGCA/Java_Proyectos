package com.monoflux.CRUDReactive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    private Integer id;
    private String marca;
    private String color;
}
