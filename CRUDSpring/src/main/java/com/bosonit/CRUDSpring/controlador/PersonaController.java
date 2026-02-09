package com.bosonit.crudspring.controlador;

import com.bosonit.crudspring.interfacesservice.InterfazPersonaServicio;
import com.bosonit.crudspring.modelo.Persona;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("persona")
public class PersonaController {

    final InterfazPersonaServicio servicio;

    public PersonaController(InterfazPersonaServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/listar")
    List<Persona> listarPersonas(){
        return servicio.listarPersonas();
    }

    @PostMapping("/agregar")
    public void nuevaPersona(Persona persona){
        servicio.guardarPersona(persona);
    }

    @PostMapping("/eliminar")
    public void borrarPersona(Persona persona){
        servicio.borrarPersona(persona.getIdPersona());
    }

    @GetMapping("/{id}")
    public List<Persona> listarPorId(@PathVariable int id) {
        return servicio.listarPorId(id);
    }
}
