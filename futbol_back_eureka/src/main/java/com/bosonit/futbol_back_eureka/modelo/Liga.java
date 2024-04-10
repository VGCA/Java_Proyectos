package com.bosonit.futbol_back_eureka.modelo;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Liga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "liga")
    private Set<Equipo> equipo;

    @OneToMany(mappedBy = "liga")
    private Set<Partido> partido;

    public Liga(){}

    public Liga(int id, Set<Equipo> equipo, Set<Partido> partido) {
        this.id = id;
        this.equipo = equipo;
        this.partido = partido;
    }
}
