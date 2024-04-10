package com.universidad.universidad.service;

import com.universidad.universidad.model.Persona;
import com.universidad.universidad.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonaServicioImpl implements PersonaServicio{
    @Autowired
    private PersonaRepository personaRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Persona> ver_personas() {
        return personaRepository.findAll();
    }

    @Override
    @Transactional
    public void guardar_persona(Persona persona) {
        personaRepository.save(persona);
    }

    @Override
    @Transactional
    public void eliminar_persona(Persona persona) {
        personaRepository.delete(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona buscar_persona(Persona persona) {
        return personaRepository.findById(persona.getId()).orElse(null);
    }
}
