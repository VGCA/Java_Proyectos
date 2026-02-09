package com.universidad.universidad.service;

import com.universidad.universidad.model.Persona;

import java.util.List;

public interface PersonaServicio {

     List<Persona> verPersonas();
     void guardarPersona(Persona persona);
     void eliminarPersona(Persona persona);
     Persona buscarPersonaPorId(Persona persona);
}
