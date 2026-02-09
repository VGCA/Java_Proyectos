package com.universidad.universidad.service;

import com.universidad.universidad.model.Persona;
import com.universidad.universidad.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonaServicioImpl implements PersonaServicio{

    private final PersonaRepository personaRepository;

    @Autowired
    public PersonaServicioImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> verPersonas() {
        return personaRepository.findAll();
    }

    @Override
    @Transactional
    public void guardarPersona(Persona persona) {
        personaRepository.save(persona);
    }

    @Override
    @Transactional
    public void eliminarPersona(Persona persona) {
        personaRepository.delete(persona);
    }

    @Override
    public Persona buscarPersonaPorId(Persona persona) {
        return personaRepository.findById(persona.getId()).orElse(null);
    }
}
