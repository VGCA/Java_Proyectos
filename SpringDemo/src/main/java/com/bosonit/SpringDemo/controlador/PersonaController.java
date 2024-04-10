package com.bosonit.SpringDemo.controlador;

import com.bosonit.SpringDemo.modelo.Persona;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonaController {

    /**
     * FUNCION QUE RETORNA UN HOLA USUARIO POR GET
     * @param nombre del usuario que se quiere saludar
     * @return saludo del usuario
     */
    @GetMapping("/user")
    String saludoInicial(@RequestParam(defaultValue = "usuario")String nombre){
        return "Hola "+nombre;
    }

    /**
     * FUNCION QUE MANDA UNA RESPUESTA POST
     * @param nombre de la persona
     * @param poblacion de la persona
     * @param edad de la persona
     * @return respuesta tipo JSON con Postman
     */
    @PostMapping("/useradd")
    Persona mostrarPersona(@RequestParam String nombre,String poblacion, int edad){
        return new Persona(nombre,poblacion,edad);
    }
}
