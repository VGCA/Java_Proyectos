package com.universidad.universidad.service;

import com.universidad.universidad.model.Persona;

import java.util.List;

public interface PersonaServicio {

     List<Persona> ver_personas();
     void guardar_persona(Persona persona);
     void eliminar_persona(Persona persona);
     Persona buscar_persona_por_id(Persona persona);
}
