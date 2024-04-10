package com.bosonit.futbol_back_eureka.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Jugador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_equipo")
    @NotNull
    private Equipo equipo;

    public Jugador(){}

    public Jugador(int id, String nombre, @NotNull Equipo equipo) {
        this.id = id;
        this.nombre = nombre;
        this.equipo = equipo;
    }
}
