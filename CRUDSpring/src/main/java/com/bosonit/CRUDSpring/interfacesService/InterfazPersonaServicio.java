package com.bosonit.crudspring.interfacesservice;

import com.bosonit.crudspring.modelo.Persona;

import java.util.List;

public interface InterfazPersonaServicio {
    List<Persona> listarPersonas();
    List<Persona> listarPorId(int id);
    int guardarPersona(Persona persona);
    void borrarPersona(int id);
}
