package com.example.demo;

import com.example.demo.controlador.CuentaController;
import com.example.demo.entidades.Cuenta;
import com.example.demo.entidades.DTO;
import com.example.demo.servicio.CuentaServicio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CuentaController.class)
public class CuentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CuentaServicio cuentaServicio;

    ObjectMapper objectMapper;

    @BeforeEach
    void configurar() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testVerDetalles() throws Exception {
        when(cuentaServicio.buscarCuentaID(1L))
                .thenReturn(Datos.crearCuenta001().orElseThrow());
        mockMvc.perform(get("/api/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.persona").value("Mario"))
                .andExpect(jsonPath("$.saldo").value("1000"));

        verify(cuentaServicio).buscarCuentaID(1L);
    }

    @Test
    void testTransferirDinero() throws Exception {
        DTO dto = new DTO();
        dto.setCuentaOrigenId(1L);
        dto.setCuentaDestinoId(2L);
        dto.setMonto(new BigDecimal("100"));
        dto.setBancoId(1L);

        System.out.println(objectMapper.writeValueAsString(dto));

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("fecha", LocalDate.now().toString());
        respuesta.put("estado", "OK");
        respuesta.put("mensaje", "Transferencia realizada");
        respuesta.put("transaccionDTO", dto);

        System.out.println(objectMapper.writeValueAsString(respuesta));

        mockMvc.perform(post("/api/transferir").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.fecha").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.mensaje").value("Transferencia realizada"))
                .andExpect(jsonPath("$.transaccionDTO.cuentaOrigenId").value(dto.getCuentaOrigenId()))
                .andExpect(content().json(objectMapper.writeValueAsString(respuesta)));
    }

    @Test
    void testListarCuentas() throws Exception {
        List<Cuenta> cuentas = Arrays.asList(Datos.crearCuenta001()
                .orElseThrow(), Datos.crearCuenta002().orElseThrow());
        when(cuentaServicio.listarCuentas()).thenReturn(cuentas);

        mockMvc.perform(get("/api").contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].persona").value("Mario"))
                .andExpect(jsonPath("$[1].persona").value("Julián"))
                .andExpect(jsonPath("$[0].saldo").value("1000"))
                .andExpect(jsonPath("$[1].saldo").value("2000"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(content().json(objectMapper.writeValueAsString(cuentas)));

        verify(cuentaServicio).listarCuentas();
    }

    @Test
    void guardarCuenta() throws JsonProcessingException,Exception {
        Cuenta cuenta = new Cuenta(null, "Míriam", new BigDecimal("8000"));
        when(cuentaServicio.guardarCuentaID(any())).then(invocar -> {
            Cuenta c = invocar.getArgument(0);
            c.setId(3L);
            return c;
        });
        mockMvc.perform(post("/api").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cuenta)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",is(3)))
                .andExpect(jsonPath("$.persona",is("Míriam")))
                .andExpect(jsonPath("$.saldo",is(8000)));

        verify(cuentaServicio).guardarCuentaID(any());
    }
}
