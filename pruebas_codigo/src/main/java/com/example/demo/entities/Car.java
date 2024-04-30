package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Car {
    private Integer id;
    private String model;
    private String color;
    private Integer kilometers;
}
