package com.bosonit.CRUDPersonas.interfaces;

import com.bosonit.CRUDPersonas.modelo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona,Integer> {
    Persona addPersona(Persona persona);
}
