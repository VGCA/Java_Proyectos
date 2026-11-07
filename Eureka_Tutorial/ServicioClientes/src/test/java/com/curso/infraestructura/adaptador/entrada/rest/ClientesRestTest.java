package com.curso.infraestructura.adaptador.entrada.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.curso.RepositorioClientesFake;
import com.curso.aplicacion.servicio.GestorClientes;

class ClientesRestTest {

    private MockMvc mockMvc;

    @BeforeEach
    void preparar() {
        GestorClientes gestor = new GestorClientes(new RepositorioClientesFake());
        mockMvc = MockMvcBuilders
                .standaloneSetup(new ClientesRest(gestor))
                .setControllerAdvice(new ManejadorErroresRest())
                .build();
    }

    @Test
    void buscarInexistenteDevuelve404() throws Exception {
        MvcResult r = mockMvc.perform(get("/clientes/nadie")).andReturn();

        assertThat(r.getResponse().getStatus()).isEqualTo(404);
        assertThat(r.getResponse().getContentAsString()).isEqualTo("Cliente no encontrado");
    }

    @Test
    void altaValidaDevuelve200YSePuedeRecuperar() throws Exception {
        String json = """
                {"login":"monty@hotmail.com","nombre":"Monty",
                 "direccionEnvio":"Calle 1","telefono":"600000000"}
                """;

        MvcResult alta = mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();
        MvcResult consulta = mockMvc.perform(get("/clientes/monty@hotmail.com")).andReturn();

        assertThat(alta.getResponse().getStatus()).isEqualTo(200);
        assertThat(consulta.getResponse().getStatus()).isEqualTo(200);
        assertThat(consulta.getResponse().getContentAsString()).contains("\"nombre\":\"Monty\"");
    }

    @Test
    void altaInvalidaDevuelve400ConLosCamposErroneos() throws Exception {
        MvcResult r = mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"login\":\"a\"}")).andReturn();

        assertThat(r.getResponse().getStatus()).isEqualTo(400);
        assertThat(r.getResponse().getContentAsString()).contains("nombre", "telefono");
    }

    @Test
    void borrarDevuelve200() throws Exception {
        MvcResult r = mockMvc.perform(delete("/clientes/a")).andReturn();

        assertThat(r.getResponse().getStatus()).isEqualTo(200);
        assertThat(r.getResponse().getContentAsString()).isEqualTo("Cliente borrado");
    }

}
