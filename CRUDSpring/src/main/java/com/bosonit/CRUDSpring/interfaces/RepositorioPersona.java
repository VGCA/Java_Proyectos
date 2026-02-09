package com.bosonit.crudspring.interfaces;

import com.bosonit.crudspring.modelo.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPersona extends CrudRepository<Persona,Integer> {
}

