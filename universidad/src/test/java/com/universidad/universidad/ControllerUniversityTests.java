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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ControllerUniversity.class)
class ControllerUniversityTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonaServicio personaServicio;

    private Persona getMockPersona() {
        Persona p = new Persona();
        p.setId(1);
        p.setNombre("Test");
        return p;
    }

    @Test
    void testAdd() throws Exception {
        mockMvc.perform(get("/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add"));
    }

    @Test
    void testGuardarSuccess() throws Exception {
        mockMvc.perform(post("/guardar")
                        .param("nombre", "Juan")
                        .param("email", "juan@test.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        Mockito.verify(personaServicio).guardar_persona(Mockito.any(Persona.class));
    }

    @Test
    void testGuardarWithErrors() throws Exception {

        mockMvc.perform(post("/guardar")
                        .param("nombre", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("modificar"));
    }

    @Test
    void testEditar() throws Exception {
        Persona mockFound = getMockPersona();

        Mockito.when(personaServicio.buscar_persona_por_id(Mockito.any(Persona.class)))
                .thenReturn(mockFound);

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

        Mockito.verify(personaServicio).eliminar_persona(Mockito.any(Persona.class));
    }
}

