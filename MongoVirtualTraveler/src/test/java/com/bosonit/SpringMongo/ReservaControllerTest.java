package com.bosonit.SpringMongo;

import com.bosonit.springmongo.controlador.ReservaController;
import com.bosonit.springmongo.modelo.Reserva;
import com.bosonit.springmongo.repositorio.ReservaRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservaController.class)
class ReservaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservaRepo reservaRepo;

    @Autowired
    private ObjectMapper objectMapper;

    private Reserva sampleReserva;

    @BeforeEach
    void setUp() {
        // Initialize a sample object for tests
        sampleReserva = new Reserva();
        // Assuming Reserva has an ID and Mes; adjust to your actual model
        sampleReserva.setId("1L");
        sampleReserva.setMes("Junio");
    }

    @Test
    void testHacerReserva() throws Exception {
        when(reservaRepo.hacerReserva(any(Reserva.class))).thenReturn(sampleReserva);

        mockMvc.perform(post("/reserva/reservar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleReserva)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.mes").value("Junio"));
    }

    @Test
    void testGetAllReservas() throws Exception {
        List<Reserva> lista = Arrays.asList(sampleReserva);
        when(reservaRepo.getAllReservas()).thenReturn(lista);

        mockMvc.perform(get("/reserva/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].mes").value("Junio"));
    }

    @Test
    void testUpdateReserva() throws Exception {
        when(reservaRepo.updateReserva(any(Reserva.class))).thenReturn(sampleReserva);

        mockMvc.perform(put("/reserva/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleReserva)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mes").value("Junio"));
    }

    @Test
    void testEliminarReserva() throws Exception {
        doNothing().when(reservaRepo).eliminarReserva(any(Reserva.class));

        mockMvc.perform(delete("/reserva/eliminar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleReserva)))
                .andExpect(status().isOk());
    }

    @Test
    void testEliminarTodo() throws Exception {
        doNothing().when(reservaRepo).borrarTodo();

        mockMvc.perform(delete("/reserva/borrartodo"))
                .andExpect(status().isOk());
    }
}
