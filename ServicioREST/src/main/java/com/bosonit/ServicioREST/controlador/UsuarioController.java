package com.bosonit.ServicioREST.controlador;

import com.bosonit.ServicioREST.modelo.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @GetMapping("/user/{id}")
    public Usuario getUsuario(@RequestParam(value = "id") int id) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
        return new Usuario();
    }
}
