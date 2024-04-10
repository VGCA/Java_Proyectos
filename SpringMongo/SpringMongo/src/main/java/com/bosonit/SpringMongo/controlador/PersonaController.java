package com.bosonit.SpringMongo.controlador;

import com.bosonit.SpringMongo.modelo.Persona;
import com.bosonit.SpringMongo.servicio.PersonaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    PersonaServicio personaServicio;

    @PostMapping("/")
    public Persona guardarPersona(@RequestBody Persona persona){
        return personaServicio.guardarPersona(persona);
    }

    @GetMapping
    public List<Persona> getAll(){
        return personaServicio.getAll();
    }

    @PutMapping("/")
    public Persona update(@RequestBody Persona persona){
        return personaServicio.update(persona);
    }

    @DeleteMapping("/")
    public long delete(@RequestBody Persona persona){
        return personaServicio.delete(persona);
    }

    @GetMapping("/nombre")
    public List<Persona> getByName(@PathParam("nombre") String nombre){
        return personaServicio.getByName(nombre);
    }
}
