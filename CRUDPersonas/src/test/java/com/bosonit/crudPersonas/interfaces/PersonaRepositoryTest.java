package com.bosonit.crudpersonas.interfaces;

import com.bosonit.crudpersonas.servicios.PersonaServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bosonit.crudpersonas.modelo.Persona;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonaRepositoryTest {
    @Mock
    private PersonaServicio personaServicio;
    @Mock
    private PersonaRepository personaRepository;
    private Persona personaModelo;

    @BeforeEach
    void init(){
        personaModelo = new Persona(1,"Pablo",12,"Madrid");
    }

    @Test
    void emptyListPersonTest(){
        assertEquals(Collections.emptyList(),personaRepository.findAll());
    }

    @Test
    void addPersonTest(){
        when(personaServicio.addPersona(personaModelo)).thenReturn(personaModelo);
        // Call the method under test
        Persona result = personaServicio.addPersona(personaModelo);
        // Verify the interaction with the mock
        verify(personaServicio).addPersona(personaModelo);
        // Assert that the result is as expected
        assertEquals(personaModelo, result);
    }
}
