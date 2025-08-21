package com.bosonit.jdbctemplate.controlador;

import com.bosonit.jdbctemplate.dtos.PersonaDTO;
import com.bosonit.jdbctemplate.modelo.Persona;
import com.bosonit.jdbctemplate.servicio.PersonaServicio;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonaController.class)
class PersonaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonaServicio personaServicio;

    @Test
    void testVerPersonas() throws Exception {
        List<Persona> personas = List.of(new Persona(1, "Juan", "Perez"));
        Mockito.when(personaServicio.findAll()).thenReturn(personas);

        mockMvc.perform(get("/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan"));
    }

    @Test
    void testGuardarPersona() throws Exception {
        PersonaDTO dto = new PersonaDTO();
        dto.setNombre("Ana");
        dto.setApellido("Lopez");

        Mockito.when(personaServicio.guardarPersona(Mockito.any(Persona.class))).thenReturn(1);

        mockMvc.perform(post("/guardar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Ana\",\"apellido\":\"Lopez\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    void testUpdatePersona() throws Exception {
        Persona existing = new Persona(1, "Carlos", "Diaz");
        Mockito.when(personaServicio.findById(1)).thenReturn(existing);
        Mockito.when(personaServicio.updatePersona(Mockito.any(Persona.class))).thenReturn(1);

        mockMvc.perform(post("/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"nombre\":\"Carlos\",\"apellido\":\"Diaz\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    void testBorrarPersona() throws Exception {
        Mockito.when(personaServicio.borrarPersona(Mockito.any(PersonaDTO.class))).thenReturn(1);

        mockMvc.perform(delete("/delete")
                        .param("id", "1")
                        .param("nombre", "Luis")
                        .param("apellido", "Martinez"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }
}
