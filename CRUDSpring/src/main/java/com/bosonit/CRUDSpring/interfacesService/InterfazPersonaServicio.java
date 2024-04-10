package com.bosonit.CRUDSpring.interfacesService;

import com.bosonit.CRUDSpring.modelo.Persona;

import java.util.List;

public interface InterfazPersonaServicio {
    List<Persona> listarPersonas();
    List<Persona> listarPorId(int id);
    int guardarPersona(Persona persona);
    void borrarPersona(int id);
}
