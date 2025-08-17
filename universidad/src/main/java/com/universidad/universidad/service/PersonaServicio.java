package com.universidad.universidad.service;

import com.universidad.universidad.model.Persona;

import java.util.List;

public interface PersonaServicio {

    public List<Persona> ver_personas();
    public void guardar_persona(Persona persona);
    public void eliminar_persona(Persona persona);
    public Persona buscar_persona_por_id(Persona persona);
}
