package com.curso.infraestructura.adaptador.entrada.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.curso.DobleDePruebas.ClientesRemotosFake;
import com.curso.DobleDePruebas.RepositorioClientesFake;
import com.curso.DobleDePruebas.RepositorioPedidosFake;
import com.curso.aplicacion.servicio.GestorPedidos;
import com.curso.dominio.modelo.Cliente;

class PedidosRestTest {

    private MockMvc mockMvc;

    @BeforeEach
    void preparar() {
        RepositorioClientesFake clientes = new RepositorioClientesFake();
        clientes.guardar(new Cliente("monty@hotmail.com", "Monty", "Calle 1", "Calle 2", "600"));
        GestorPedidos gestor = new GestorPedidos(clientes, new RepositorioPedidosFake(), new ClientesRemotosFake());
        mockMvc = MockMvcBuilders
                .standaloneSetup(new PedidosRest(gestor))
                .setControllerAdvice(new ManejadorErroresRest())
                .build();
    }

    @Test
    void buscarInexistenteDevuelve404() throws Exception {
        MvcResult r = mockMvc.perform(get("/pedidos/99")).andReturn();

        assertThat(r.getResponse().getStatus()).isEqualTo(404);
        assertThat(r.getResponse().getContentAsString()).isEqualTo("Pedido no encontrado");
    }

    @Test
    void altaValidaDevuelve200ConIdAsignado() throws Exception {
        String json = """
                {"codigo":"PED-0","fecha":"Hoy","estado":"Pendiente",
                 "cliente":{"login":"monty@hotmail.com"}}
                """;

        MvcResult r = mockMvc.perform(post("/pedidos")
                .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();

        assertThat(r.getResponse().getStatus()).isEqualTo(200);
        assertThat(r.getResponse().getContentAsString()).contains("\"id\":1", "\"nombre\":\"Monty\"");
    }

    @Test
    void altaSinEstadoDevuelve400() throws Exception {
        String json = """
                {"codigo":"PED-0","fecha":"Hoy","cliente":{"login":"monty@hotmail.com"}}
                """;

        MvcResult r = mockMvc.perform(post("/pedidos")
                .contentType(MediaType.APPLICATION_JSON).content(json)).andReturn();

        assertThat(r.getResponse().getStatus()).isEqualTo(400);
        assertThat(r.getResponse().getContentAsString()).contains("estado");
    }

}
