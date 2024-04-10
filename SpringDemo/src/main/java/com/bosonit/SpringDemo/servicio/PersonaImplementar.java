package com.bosonit.SpringDemo.servicio;

import com.bosonit.SpringDemo.modelo.Ciudad;
import com.bosonit.SpringDemo.modelo.Persona;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonaImplementar implements InterfazPersona{

    ArrayList<Persona> listaPersonas = new ArrayList<>();
    ArrayList<Ciudad> listaCiudades = new ArrayList<>();

    /**
     * FUNCION QUE DEVUELVE UN OBJETO PERSONA CREADO
     * @param nombre
     * @param poblacion
     * @param edad
     * @return persona creada
     */
    @Override
    public Persona crearPersona(String nombre, String poblacion, Integer edad) {
        Persona nuevaPersona = new Persona(nombre,poblacion,edad);
        listaPersonas.add(nuevaPersona);
        return nuevaPersona;
    }

    /**
     * METODO QUE ALMACENA UNA NUEVA CIUDAD
     * @param ciudad
     */
    @Override
    public void addCiudad(Ciudad ciudad) {
        listaCiudades.add(ciudad);
    }

    @Override
    public List<Persona> getPersonas() {
        return null;
    }

    @Override
    public List<Ciudad> getCiudades() {
        return null;
    }
}
