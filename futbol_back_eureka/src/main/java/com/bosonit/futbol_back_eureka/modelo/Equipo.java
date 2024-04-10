package com.bosonit.futbol_back_eureka.modelo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Equipo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titulo;

    @OneToMany(mappedBy = "equipo")
    private Set<Jugador> jugador;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_liga")
    private Liga liga;

    public Equipo(){}

    public Equipo(int id, String titulo, Set<Jugador> jugador, Liga liga) {
        this.id = id;
        this.titulo = titulo;
        this.jugador = jugador;
        this.liga = liga;
    }
}
