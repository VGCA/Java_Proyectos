package com.bosonit.crudpersonas.controller;

import com.bosonit.crudpersonas.servicios.PersonaServicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("persona")
@RestController
@AllArgsConstructor
public class PersonaController {

    private PersonaServicio servicio;

}
