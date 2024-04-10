package com.bosonit.futbol_back_eureka.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bosonit.futbol_back_eureka.modelo.Partido;



public interface PartidoRepo extends JpaRepository<Partido,Integer>{
    
}
