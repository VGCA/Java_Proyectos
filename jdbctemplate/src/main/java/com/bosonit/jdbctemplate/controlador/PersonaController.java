package com.bosonit.jdbctemplate.controlador;

import java.util.List;

import com.bosonit.jdbctemplate.dtos.PersonaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bosonit.jdbctemplate.modelo.Persona;
import com.bosonit.jdbctemplate.servicio.PersonaServicio;

@RestController
public class PersonaController {

    private final PersonaServicio personaServicio;

    @Autowired
    public PersonaController(PersonaServicio personaServicio) {
        this.personaServicio = personaServicio;
    }

    @GetMapping("/listar")
    public List<Persona> verPersonas(){
        return personaServicio.findAll();
    }

    @PostMapping("/guardar")
    public int guardarPersona(@RequestBody PersonaDTO personaDTO){
        Persona savePersona = new Persona();
        savePersona.setNombre(personaDTO.getNombre());
        savePersona.setApellido(personaDTO.getApellido());
        return personaServicio.guardarPersona(savePersona);
    }

    @PostMapping("/update")
    public int updatePersona(@RequestBody PersonaDTO dto) {
        Persona persona = personaServicio.findById(dto.getId());
        persona.setNombre(dto.getNombre());
        persona.setApellido(dto.getApellido());
        return personaServicio.updatePersona(persona);
    }

    @DeleteMapping("/delete")
    public int borrarPersona(@RequestParam PersonaDTO e) {
        return personaServicio.borrarPersona(e);
    }
}