package com.testing.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Caja<T,W>{
    private T datos;
    private W operaciones;
}
