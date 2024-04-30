package com.testing.demo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PersonaTest {

    private Persona persona;

    @BeforeEach
    void setUp() {
        persona=new Persona();
        persona.setNombre("firstNombre");
        persona.setEdad(1);
        persona.setPuesto("firstPuesto");
    }

    @Test
    void getDataPersonaTest(){
        assertEquals(persona,persona);
    }

    @Test
    void changeDataTest(){
        persona.setNombre("secondNombre");
        assertEquals("secondNombre",persona.getNombre());
    }
}