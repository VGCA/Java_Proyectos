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
public class Partido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titulo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_liga")
    private Liga liga;

    @OneToMany(mappedBy = "partido")
    private Set<Evento> evento;

    public Partido(){}

    public Partido(int id, String titulo, Liga liga, Set<Evento> evento) {
        this.id = id;
        this.titulo = titulo;
        this.liga = liga;
        this.evento = evento;
    }
}
