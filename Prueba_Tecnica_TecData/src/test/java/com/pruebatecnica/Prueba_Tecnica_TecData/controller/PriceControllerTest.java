package com.pruebatecnica.Prueba_Tecnica_TecData.controller;

import com.pruebatecnica.Prueba_Tecnica_TecData.model.Price;
import com.pruebatecnica.Prueba_Tecnica_TecData.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    private Price priceExample1, priceExample2;

    @BeforeEach
    void setUp() {
        priceExample1 = new Price(1,
                LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59),
                1,
                35455,
                0,
                new BigDecimal("35.50"),
                "EUR");

        priceExample2 = new Price(1,
                LocalDateTime.of(2020, 6, 14, 15, 0),
                LocalDateTime.of(2020, 6, 14, 18, 30),
                2,
                35455,
                1,
                new BigDecimal("25.45"),
                "EUR");
    }

    @Test
    void expectedCode200Item1Exists() {
        when(priceService.getApplicablePrice(anyInt(), anyInt(), any(LocalDateTime.class)))
                .thenReturn(Optional.of(priceExample1));

        Optional<Price> response = priceController.getPrice(35455, 1, LocalDateTime.of(2020, 6, 14, 0, 0));

        assertTrue(response.isPresent());
        assertEquals(priceExample1, response.get());

        verify(priceService, times(1)).getApplicablePrice(anyInt(), anyInt(), any(LocalDateTime.class));
    }

    @Test
    void expectedCode200Item2Exists() {

        when(priceService.getApplicablePrice(anyInt(), anyInt(), any(LocalDateTime.class)))
                .thenReturn(Optional.of(priceExample2));

        Optional<Price> response = priceController.getPrice(35455, 1, LocalDateTime.of(2020, 6, 14, 15, 0));

        assertTrue(response.isPresent());
        assertEquals(priceExample2, response.get());  

        verify(priceService, times(1)).getApplicablePrice(anyInt(), anyInt(), any(LocalDateTime.class));
    }
}


