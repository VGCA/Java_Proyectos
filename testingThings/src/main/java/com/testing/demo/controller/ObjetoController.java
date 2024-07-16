package com.testing.demo.controller;

import com.testing.demo.dto.PersonaDTO;
import com.testing.demo.mapper.MapperDTO;
import com.testing.demo.model.Dog;
import com.testing.demo.model.Persona;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/object")
public class ObjetoController {

    private MapperDTO mapperDTO;

    @GetMapping
    @ApiOperation(value = "Obtener saludos", notes = "Recibir un saludo")
    public String saludar() {
        return "Hola";
    }

    @GetMapping("/{id}")
    public String saludoId(
            @ApiParam(value = "id user", required = true
                    , example = "1")
            @PathVariable Integer id) {
        return "Hola " + id;
    }

    @PostMapping
    @ApiOperation(value = "Crear nuevo coche", notes = "Crear nuevo coche")
    public String createObject(
            @ApiParam(value = "Crear coche", required = true)
            @RequestBody Dog dog) {
        return "Se ha creado " + dog;
    }

    @PostMapping("/post")
    public PersonaDTO changePersonaToDto(@RequestBody Persona persona){
        return mapperDTO.changePersonaToDto(persona);
    }
}
