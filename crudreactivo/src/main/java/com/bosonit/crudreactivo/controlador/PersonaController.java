package com.bosonit.crudreactivo.controlador;

import com.bosonit.crudreactivo.modelo.Persona;
import com.bosonit.crudreactivo.servicio.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    /*@GetMapping("/verlista")
    public Flux<Persona> verPersonas() {
        return personaService.verPersonas();
    }

    @PostMapping("/guardar")
    public Mono<Persona> agregarPersona(@RequestBody Persona p){
        return personaService.ingresarPersona(p);
    }*/

    @GetMapping
    public String verPersonas(Model model) {
        model.addAttribute("users", personaService.verPersonas());
        return "index";
    }

    @PostMapping("/add")
    public String agregarPersonas(@RequestBody Persona persona, Model model) {
        personaService.ingresarPersona(persona);
        return "redirect:/index";
    }
}
