package com.bosonit.SpringDemo.controlador;

import com.bosonit.SpringDemo.modelo.Ciudad;
import com.bosonit.SpringDemo.modelo.Persona;
import com.bosonit.SpringDemo.servicio.InterfazPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controlador2 {
    @Autowired
    InterfazPersona interfaz;
    @Autowired
    Controlador1 controller1;

    @GetMapping("/controlador2/getPersona")
    Persona getPersona(){
        return controller1.getPersona(null,null,null);
    }

    @GetMapping("getPersonas")
    public List<Persona> verListaPersonas() {
        return interfaz.getPersonas();
    }

    @GetMapping("getCiudades")
    public List<Ciudad> verListaCiudades() {
        return interfaz.getCiudades();
    }
}
