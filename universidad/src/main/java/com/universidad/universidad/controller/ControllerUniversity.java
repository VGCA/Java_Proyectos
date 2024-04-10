package com.universidad.universidad.controller;

import com.universidad.universidad.model.Persona;
import com.universidad.universidad.service.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class ControllerUniversity {
    @Autowired
    private PersonaServicio personaServicio;

    @GetMapping("/")
    public String index(Model model) {
        log.info("Mostrando index");
        List<Persona> listPersonas = personaServicio.ver_personas();
        model.addAttribute("listPersonas", listPersonas);
        return "index";
    }

    // ADD NEW PERSON TO DATABASE

    @GetMapping("/add")
    public String add(Persona persona) {
        return "add";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors error) {
        if(error.hasErrors()){
            return "modificar";
        }
        personaServicio.guardar_persona(persona);
        return "redirect:/";
    }

    // EDIT A PERSON FROM THE DATABASE

    @GetMapping("/edit/{id}")
    public String editar(Persona persona, Model model) {
        Persona personaFind = personaServicio.buscar_persona(persona);
        model.addAttribute("personaFind", personaFind);
        return "modificar";
    }

    // DELETE A PERSON FROM THE DATABASE

    @GetMapping("/delete/{id}")
    public String borrar(Persona persona) {
        personaServicio.eliminar_persona(persona);
        return "redirect:/";
    }
}
