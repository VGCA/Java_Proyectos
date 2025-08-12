package com.bosonit.crudpersonas.servicios;

import com.bosonit.crudpersonas.interfaces.PersonaRepository;
import com.bosonit.crudpersonas.modelo.Persona;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonaServicio{

    private PersonaRepository personaRepository;

    public Persona addPersona(Persona persona){
        return personaRepository.addPersona(persona);
    }
}
