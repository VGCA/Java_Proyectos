package com.bosonit.SpringDemo.controlador;

import com.bosonit.SpringDemo.modelo.Ciudad;
import com.bosonit.SpringDemo.modelo.Persona;
import com.bosonit.SpringDemo.servicio.InterfazPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controlador1 {

    @Autowired
    InterfazPersona interfaz;

    @GetMapping("/controlador1/addPersona")
    public Persona getPersona(@RequestHeader String nombre,
                              @RequestHeader String poblacion,
                              @RequestHeader int edad){
        return new Persona(nombre,poblacion,edad);
    }

    @GetMapping("/controlador1/addCiudad")
    public Ciudad setCiudad(@RequestHeader Ciudad ciudad) {
        interfaz.addCiudad(ciudad);
        return ciudad;
    }
}
