package com.bosonit.crudpersonas.interfaces;

import com.bosonit.crudpersonas.modelo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona,Integer> {
    Persona addPersona(Persona persona);
}
