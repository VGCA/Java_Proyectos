package com.bosonit.juego4enraya.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosonit.juego4enraya.modelo.Persona;
import com.bosonit.juego4enraya.repositorio.PersonaRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class PersonaController {
    
    private final PersonaRepo personaRepo;

    public PersonaController(PersonaRepo personaRepo) {
        this.personaRepo = personaRepo;
    }

    @GetMapping
    public Flux<Persona> obtenerPersonas() {
        return personaRepo.findAll();
    }

    @PostMapping("/añadir")
    public Mono<Persona> agregarPersona(@RequestBody Persona persona) {
        return personaRepo.save(persona);
    }
}