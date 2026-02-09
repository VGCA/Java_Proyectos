package com.universidad.universidad.controller;

import com.universidad.universidad.model.Persona;
import com.universidad.universidad.service.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class ControllerUniversity {

    private final PersonaServicio personaServicio;
    Logger log = Logger.getLogger(ControllerUniversity.class.getName());


    @Autowired
    public ControllerUniversity(PersonaServicio personaServicio) {
        this.personaServicio = personaServicio;
    }

    @GetMapping("/")
    public String index(Model model) {
        log.info("Mostrando index");
        List<Persona> listPersonas = personaServicio.ver_personas();
        model.addAttribute("listPersonas", listPersonas);
        return "index";
    }


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


    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") Integer id, Persona persona, Model model) {
        Persona personaFind = personaServicio.buscar_persona_por_id(persona);
        model.addAttribute("personaFind", personaFind);
        return "modificar";
    }


    @GetMapping("/delete/{id}")
    public String borrar(@PathVariable("id") Integer id, Persona persona) {
        personaServicio.eliminar_persona(persona);
        return "redirect:/";
    }
}
