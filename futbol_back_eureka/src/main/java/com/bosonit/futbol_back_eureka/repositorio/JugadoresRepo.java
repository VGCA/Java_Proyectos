package com.bosonit.futbol_back_eureka.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bosonit.futbol_back_eureka.modelo.Jugador;



public interface JugadoresRepo extends JpaRepository<Jugador,Integer>{
    
}
