package com.bosonit.jdbctemplate.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bosonit.jdbctemplate.modelo.Persona;
import com.bosonit.jdbctemplate.servicio.PersonaServicio;

@RestController
public class PersonaController {
    
    @Autowired
    private PersonaServicio personaServicio;

    @GetMapping("/listar")
    public List<Persona> verPersonas(){
        return personaServicio.findAll();
    }

    @PostMapping("/guardar")
    public int guardarPersona(@RequestParam Persona e){
        return personaServicio.guardarPersona(e);
    }

    @PostMapping("/update")
    public int updatePersona(@RequestParam Persona e) {
        return personaServicio.updatePersona(e);
    }

    @DeleteMapping("/delete")
    public int borrarPersona(@RequestParam Persona e) {
        return personaServicio.borrarPersona(e);
    }
}
