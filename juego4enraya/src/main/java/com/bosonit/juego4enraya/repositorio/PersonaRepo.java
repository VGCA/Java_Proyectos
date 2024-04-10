package com.bosonit.juego4enraya.repositorio;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bosonit.juego4enraya.modelo.Persona;

@Repository
public interface PersonaRepo extends ReactiveCrudRepository<Persona, Integer>{
}
