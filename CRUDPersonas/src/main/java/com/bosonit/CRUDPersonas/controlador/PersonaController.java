package com.bosonit.CRUDPersonas.controlador;

import com.bosonit.CRUDPersonas.modelo.Persona;
import com.bosonit.CRUDPersonas.servicios.PersonaServicio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("persona")
@RestController
@AllArgsConstructor
public class PersonaController {

    private PersonaServicio servicio;

}
