package com.universidad.universidad.controller;

import com.universidad.universidad.model.Persona;
import com.universidad.universidad.service.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ControllerUniversity {

    private final PersonaServicio personaServicio;

    @Autowired
    public ControllerUniversity(PersonaServicio personaServicio) {
        this.personaServicio = personaServicio;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Persona> listPersonas = personaServicio.verPersonas();
        model.addAttribute("listPersonas", listPersonas);
        return "index";
    }


    @GetMapping("/add")
    public String add(Persona persona) {
        return "add";
    }

    @PostMapping("/guardar")
    public String guardar(Persona persona, Errors error) {
        if(error.hasErrors()){
            return "modificar";
        }
        personaServicio.guardarPersona(persona);
        return "redirect:/";
    }


    @GetMapping("/edit")
    public String editar(Persona persona, Model model) {
        Persona personaFind = personaServicio.buscarPersonaPorId(persona);
        model.addAttribute("personaFind", personaFind);
        return "modificar";
    }


    @GetMapping("/delete")
    public String borrar(Persona persona) {
        personaServicio.eliminarPersona(persona);
        return "redirect:/";
    }
}
