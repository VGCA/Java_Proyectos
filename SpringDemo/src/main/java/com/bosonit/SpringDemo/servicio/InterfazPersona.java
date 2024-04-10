package com.bosonit.SpringDemo.servicio;

import com.bosonit.SpringDemo.modelo.Ciudad;
import com.bosonit.SpringDemo.modelo.Persona;

import java.util.List;

public interface InterfazPersona {
    Persona crearPersona(String nombre, String poblacion, Integer edad);

    void addCiudad(Ciudad ciudad);

    List<Persona> getPersonas();

    List<Ciudad> getCiudades();
}
