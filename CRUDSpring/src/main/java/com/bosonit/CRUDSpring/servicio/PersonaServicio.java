package com.bosonit.crudspring.servicio;

import com.bosonit.crudspring.exceptionConfig.NotFoundException;
import com.bosonit.crudspring.exceptionConfig.UnprocesableException;
import com.bosonit.crudspring.interfaces.RepositorioPersona;
import com.bosonit.crudspring.interfacesservice.InterfazPersonaServicio;
import com.bosonit.crudspring.modelo.Persona;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServicio implements InterfazPersonaServicio {

    final RepositorioPersona repositorioPersona;

    ArrayList<Persona> listaPersonas = new ArrayList<>();

    public PersonaServicio(RepositorioPersona repositorioPersona) {
        this.repositorioPersona = repositorioPersona;
    }

    @Override
    public List<Persona> listarPersonas() {
        if(listaPersonas.size()==0){
            Timestamp instant= Timestamp.from(Instant.now());
            throw new NotFoundException("No hay registros",404,instant);
        }
        return (List<Persona>)repositorioPersona.findAll();
    }

    @Override
    public List<Persona> listarPorId(int id){
        List<Persona> personaEncontrada = (List<Persona>)listaPersonas.get(id);
        if(personaEncontrada.size()==0){
            Timestamp instant= Timestamp.from(Instant.now());
            throw new NotFoundException("No se encuentra la persona insertada",404,instant);
        }
        return personaEncontrada;
    }

    @Override
    public int guardarPersona(Persona persona) {
        if(listaPersonas.contains(persona)){
            Timestamp instant= Timestamp.from(Instant.now());
            throw new UnprocesableException("No se encuentra la persona insertada",422,
                    instant);
        }
        listaPersonas.add(persona);
        return 0;
    }

    @Override
    public void borrarPersona(int id) {
        if(listaPersonas.contains(id)){
            listaPersonas.remove(id);
        }else{
            Timestamp instant= Timestamp.from(Instant.now());
            throw new NotFoundException("No se puede borrar la persona que no existe",404,
                    instant);
        }
    }
}
