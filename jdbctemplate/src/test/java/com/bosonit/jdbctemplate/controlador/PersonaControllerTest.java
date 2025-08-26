package com.bosonit.jdbctemplate.controlador;

import com.bosonit.jdbctemplate.dtos.PersonaDTO;
import com.bosonit.jdbctemplate.modelo.Persona;
import com.bosonit.jdbctemplate.servicio.PersonaServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PersonaControllerTest {

    @InjectMocks
    private PersonaController personaController;

    @Mock
    private PersonaServicio personaServicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testVerPersonas() {
        Persona p1 = new Persona(1, "Juan", "Perez");
        Persona p2 = new Persona(2, "Ana", "Gomez");
        when(personaServicio.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Persona> result = personaController.verPersonas();

        assertEquals(2, result.size());
        verify(personaServicio).findAll();
    }

    @Test
    void testGuardarPersona() {
        PersonaDTO dto = new PersonaDTO();
        dto.setNombre("Carlos");
        dto.setApellido("Lopez");

        when(personaServicio.guardarPersona(any(Persona.class))).thenReturn(1);

        int result = personaController.guardarPersona(dto);

        assertEquals(1, result);
        verify(personaServicio).guardarPersona(any(Persona.class));
    }

    @Test
    void testUpdatePersona() {
        PersonaDTO dto = new PersonaDTO();
        dto.setId(1);
        dto.setNombre("Luis");
        dto.setApellido("Martinez");

        Persona existing = new Persona(1, "OldName", "OldSurname");
        when(personaServicio.findById(1)).thenReturn(existing);
        when(personaServicio.updatePersona(any(Persona.class))).thenReturn(1);

        int result = personaController.updatePersona(dto);

        assertEquals(1, result);
        verify(personaServicio).findById(1);
        verify(personaServicio).updatePersona(existing);
        assertEquals("Luis", existing.getNombre());
        assertEquals("Martinez", existing.getApellido());
    }

    @Test
    void testBorrarPersona() {
        PersonaDTO dto = new PersonaDTO();
        dto.setId(3);
        dto.setNombre("Delete");
        dto.setApellido("Me");

        when(personaServicio.borrarPersona(dto)).thenReturn(1);

        int result = personaController.borrarPersona(dto);

        assertEquals(1, result);
        verify(personaServicio).borrarPersona(dto);
    }
}
