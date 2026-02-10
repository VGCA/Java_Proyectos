package com.bosonit.crudreactivo.servicio;

import com.bosonit.crudreactivo.modelo.Persona;
import com.bosonit.crudreactivo.repo.PersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonaService {

    private final PersonaRepo personaRepo;

    public PersonaService(PersonaRepo personaRepo) {
        this.personaRepo = personaRepo;
    }

    public Mono<Persona> ingresarPersona(Persona persona){
        return personaRepo.save(persona);
    }

    public Flux<Persona> verPersonas(){
        return personaRepo.findAll();
    }
}
