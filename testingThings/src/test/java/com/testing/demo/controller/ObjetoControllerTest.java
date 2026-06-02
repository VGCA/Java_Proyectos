package com.testing.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testing.demo.dto.PersonaDTO;
import com.testing.demo.mapper.MapperDTO;
import com.testing.demo.model.Dog;
import com.testing.demo.model.Persona;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ObjetoController.class)
class ObjetoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MapperDTO mapperDTO;



    @Test
    void saludar_deberiaRetornarHola() throws Exception {
        mockMvc.perform(get("/api/object"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hola"));
    }



    @Test
    void saludoId_conIdValido_deberiaRetornarHolaMasId() throws Exception {
        mockMvc.perform(get("/api/object/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hola 5"));
    }

    @Test
    void saludoId_conIdNoNumerico_deberiaRetornar400() throws Exception {
        mockMvc.perform(get("/api/object/abc"))
                .andExpect(status().isBadRequest());
    }



    @Test
    void createObject_conDogValido_deberiaRetornar200() throws Exception {
        Dog dog = new Dog();


        mockMvc.perform(post("/api/object")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dog)))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Se ha creado")));
    }

    @Test
    void createObject_sinBody_deberiaRetornar400() throws Exception {
        mockMvc.perform(post("/api/object")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }



    @Test
    void changePersonaToDto_deberiaRetornarDTO() throws Exception {
        Persona persona = new Persona();


        PersonaDTO dto = new PersonaDTO();


        when(mapperDTO.changePersonaToDto(any(Persona.class))).thenReturn(dto);

        mockMvc.perform(post("/api/object/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(persona)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void changePersonaToDto_sinBody_deberiaRetornar400() throws Exception {
        mockMvc.perform(post("/api/object/post")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}