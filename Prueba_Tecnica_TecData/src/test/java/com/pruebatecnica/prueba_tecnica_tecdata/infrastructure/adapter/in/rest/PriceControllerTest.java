package com.pruebatecnica.prueba_tecnica_tecdata.infrastructure.adapter.in.rest;

import com.pruebatecnica.prueba_tecnica_tecdata.application.port.in.GetApplicablePriceUseCase;
import com.pruebatecnica.prueba_tecnica_tecdata.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceController.class)
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GetApplicablePriceUseCase getApplicablePriceUseCase;

    @Test
    void returns200WithThePriceWhenOneApplies() throws Exception {
        Price price = new Price(1, LocalDateTime.of(2020, 6, 14, 15, 0), LocalDateTime.of(2020, 6, 14, 18, 30),
                2, 35455, 1, new BigDecimal("25.45"), "EUR");
        when(getApplicablePriceUseCase.getApplicablePrice(any(), any(), any())).thenReturn(Optional.of(price));

        mockMvc.perform(get("/api/prices")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-14T16:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.price").value(25.45))
                .andExpect(jsonPath("$.currency").value("EUR"));
    }

    @Test
    void returns404WhenNoPriceApplies() throws Exception {
        when(getApplicablePriceUseCase.getApplicablePrice(any(), any(), any())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/prices")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("applicationDate", "2019-01-01T00:00:00"))
                .andExpect(status().isNotFound());
    }

    @Test
    void returns400WhenAParameterIsMissing() throws Exception {
        mockMvc.perform(get("/api/prices").param("productId", "35455"))
                .andExpect(status().isBadRequest());
    }
}
