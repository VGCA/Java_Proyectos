package com.bosonit.crudreactivo;

import com.bosonit.crudreactivo.controlador.PersonaController;
import com.bosonit.crudreactivo.modelo.Persona;
import com.bosonit.crudreactivo.servicio.PersonaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Flux;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PersonaController.class)
class PersonaViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonaService personaService;

    @Test
    void testVerPersonas_View() throws Exception {

        Mockito.when(personaService.verPersonas()).thenReturn(Flux.just(new Persona(1, "Alice")));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    void testAgregarPersonas_Redirect() throws Exception {
        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\", \"nombre\":\"Alice\"}"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));

        verify(personaService, times(1)).ingresarPersona(any());
    }
}
