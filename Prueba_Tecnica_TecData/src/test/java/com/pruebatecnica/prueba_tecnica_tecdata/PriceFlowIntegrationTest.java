package com.pruebatecnica.prueba_tecnica_tecdata;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** Exercises REST adapter -> use case -> persistence adapter -> H2 with the real sample data. */
@SpringBootTest
@AutoConfigureMockMvc
class PriceFlowIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest(name = "{0} -> price list {1}, {2} EUR")
    @CsvSource({
            "2020-06-14T10:00:00, 1, 35.50",
            "2020-06-14T16:00:00, 2, 25.45",
            "2020-06-14T21:00:00, 1, 35.50",
            "2020-06-15T10:00:00, 3, 30.50",
            "2020-06-16T21:00:00, 4, 38.95"
    })
    void returnsTheExpectedPriceForEachDate(String date, int priceList, double price) throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("applicationDate", date))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.priceList").value(priceList))
                .andExpect(jsonPath("$.price").value(price));
    }

    @Test
    void returns404ForAnUnknownProduct() throws Exception {
        mockMvc.perform(get("/api/prices")
                        .param("productId", "99999")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-14T10:00:00"))
                .andExpect(status().isNotFound());
    }
}
