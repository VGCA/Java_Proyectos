package com.bosonit.CRUDSpring.controlador;

import com.bosonit.CRUDSpring.interfacesService.InterfazPersonaServicio;
import com.bosonit.CRUDSpring.modelo.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("persona")
public class PersonaController {

    @Autowired
    InterfazPersonaServicio servicio;

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
        servicio.borrarPersona(persona.getId_persona());
    }

    @GetMapping("/{id}")
    public Optional<Persona> listarPorId(int id){
        return servicio.listarPorId(id);
    }
}
