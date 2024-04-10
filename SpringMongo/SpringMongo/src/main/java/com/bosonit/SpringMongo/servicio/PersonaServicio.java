package com.bosonit.SpringMongo.servicio;

import com.bosonit.SpringMongo.modelo.Persona;
import com.bosonit.SpringMongo.repositorio.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServicio {

    @Autowired
    PersonaRepositorio personaRepositorio;

    /**
     * FUNCION QUE GUARDA UNA PERSONA
     * @param persona
     * @return
     */
    public Persona guardarPersona(Persona persona) {
        return personaRepositorio.guardarPersona(persona);
    }

    /**
     * FUNCION QUE RETORNA TODAS LAS PERSONAS
     * REGISTRADAS EN LA BDD
     * @return
     */
    public List<Persona> getAll() {
        return personaRepositorio.find();
    }

    /**
     * FUNCION QUE ACTUALIZA UNA PERSONA
     * SI ES QUE EXISTE
     * @param persona
     * @return
     */
    public Persona update(Persona persona) {
        /* Gracias a Mongo se puede rescribir el dato */
        return personaRepositorio.guardarPersona(persona);
    }

    /**
     * FUNCION QUE RETORNA 1 SI SE ELIMINA
     * CON ÉXITO UNA PERSONA DE LA BDD
     * @param persona
     * @return
     */
    public long delete(Persona persona) {
        return personaRepositorio.delete(persona);
    }

    /**
     * FUNCIÓN QUE RECIBE UNA PERSONA
     * POR SU NOMBRE SI EXISTE
     * @param nombre
     * @return
     */
    public List<Persona> getByName(String nombre) {
        return personaRepositorio.getByName(nombre);
    }
}
