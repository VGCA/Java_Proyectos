package com.bosonit.futbol_back_eureka.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bosonit.futbol_back_eureka.modelo.Liga;



public interface LigaRepo extends JpaRepository<Liga,Integer>{
    
}
