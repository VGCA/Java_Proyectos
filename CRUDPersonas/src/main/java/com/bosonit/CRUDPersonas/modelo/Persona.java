package com.bosonit.CRUDPersonas.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data @AllArgsConstructor
public class Persona {
    @Id
    @GeneratedValue
    private Integer id;
    private String nombre;
    private Integer edad;
    private String poblacion;
}
