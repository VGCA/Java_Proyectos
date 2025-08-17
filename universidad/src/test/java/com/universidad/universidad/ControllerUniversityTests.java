package com.universidad.universidad;

import com.universidad.universidad.controller.ControllerUniversity;
import com.universidad.universidad.model.Persona;
import com.universidad.universidad.service.PersonaServicio;
import org.junit.jupiter.api.Test;
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

    public Persona createPersonaTest(){
        return new Persona(1,"test","test","test@test.com","123");
    }

    @Test
    void testIndex() throws Exception {
        Persona persona = new Persona();
        Mockito.when(personaServicio.ver_personas()).thenReturn(Arrays.asList(persona));

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
    void testGuardarValid() throws Exception {
        mockMvc.perform(post("/guardar")
                        .param("nombre", "Juan") // Add required fields here
                        .param("edad", "30"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void testEditar() throws Exception {
        Persona persona = new Persona();
        Mockito.when(personaServicio.buscar_persona_por_id(createPersonaTest())).thenReturn(persona);

        mockMvc.perform(get("/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("modificar"))
                .andExpect(model().attributeExists("personaFind"));
    }

    @Test
    void testBorrar() throws Exception {
        mockMvc.perform(get("/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}

