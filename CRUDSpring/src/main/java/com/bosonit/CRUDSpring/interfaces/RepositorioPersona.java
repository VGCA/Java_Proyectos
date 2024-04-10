package com.bosonit.CRUDSpring.interfaces;

import com.bosonit.CRUDSpring.modelo.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface InterfazPersona extends CrudRepository<Persona,Integer> {
}// Esto es un repositorio

public interface RepositorioPersona extends CrudRepository<Persona,Integer> {
}

