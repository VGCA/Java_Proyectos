package com.bosonit.CRUDPersonas.servicios;

import com.bosonit.CRUDPersonas.interfaces.PersonaRepository;
import com.bosonit.CRUDPersonas.modelo.Persona;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
@AllArgsConstructor
public class PersonaServicio{

    private PersonaRepository personaRepository;

    public Persona addPersona(Persona persona){
        return personaRepository.addPersona(persona);
    }
}
