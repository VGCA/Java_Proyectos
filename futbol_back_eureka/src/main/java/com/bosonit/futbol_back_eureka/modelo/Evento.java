package com.bosonit.futbol_back_eureka.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Evento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean acontecimientos;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_partido")
    private Partido partido;

    public Evento(){}

    public Evento(int id, boolean acontecimientos, Partido partido) {
        this.id = id;
        this.acontecimientos = acontecimientos;
        this.partido = partido;
    }
}
