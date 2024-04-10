package com.bosonit.crudreactivo.repo;

import com.bosonit.crudreactivo.modelo.Persona;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepo extends ReactiveCrudRepository<Persona, Integer> {
}
