package com.bosonit.crudpersonas.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
public class Persona {
    @Id
    @GeneratedValue
    private Integer id;
    private String nombre;
    private Integer edad;
    private String poblacion;

    public Persona(Integer id, String nombre, Integer edad, String poblacion) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.poblacion = poblacion;
    }
}
