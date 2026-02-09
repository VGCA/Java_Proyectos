package com.universidad.universidad.controller;

import org.junit.jupiter.api.Test;

import com.universidad.universidad.model.Persona;
import com.universidad.universidad.service.PersonaServicio;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ControllerUniversity.class)
class ControllerUniversityTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonaServicio personaServicio;

    @Test
    void testIndex() throws Exception {
        Persona p = new Persona(1, "Juan", "Perez", "juan@mail.com", "123");
        Mockito.when(personaServicio.verPersonas()).thenReturn(Arrays.asList(p));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("listPersonas"));
    }

    @Test
    void testAdd() throws Exception {
        mockMvc.perform(get("/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add"));
    }

    @Test
    void testGuardarSuccess() throws Exception {
        // We simulate the form submission with params
        mockMvc.perform(post("/guardar")
                        .param("nombre", "Juan")
                        .param("apellido", "Perez")
                        .param("email", "juan@test.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        Mockito.verify(personaServicio).guardarPersona(Mockito.any(Persona.class));
    }

    @Test
    void testEditar() throws Exception {
        Persona mockFound = new Persona(1, "Juan", "Perez", "j@mail.com", "123");

        // Match the service call exactly
        Mockito.when(personaServicio.buscarPersonaPorId(Mockito.any(Persona.class)))
                .thenReturn(mockFound);

        mockMvc.perform(get("/edit").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("modificar"))
                .andExpect(model().attributeExists("personaFind"));
    }

    @Test
    void testBorrar() throws Exception {
        mockMvc.perform(get("/delete").param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        Mockito.verify(personaServicio).eliminarPersona(Mockito.any(Persona.class));
    }
}