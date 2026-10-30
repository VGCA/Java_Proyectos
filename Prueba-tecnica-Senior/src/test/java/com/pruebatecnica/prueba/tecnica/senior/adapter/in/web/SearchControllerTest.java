package com.pruebatecnica.prueba.tecnica.senior.adapter.in.web;

import com.pruebatecnica.prueba.tecnica.senior.application.port.in.CreateSearchUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SearchController.class)
class SearchControllerTest {

    private final MockMvc mockMvc;

    @MockitoBean
    private CreateSearchUseCase createSearchUseCase;

    @Autowired
    SearchControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void testCreateSearch() throws Exception {
        when(createSearchUseCase.createSearch(any())).thenReturn("abc-123");

        String requestBody = """
        {
            "hotelId": "1234aBc",
            "checkIn": "29/12/2023",
            "checkOut": "31/12/2023",
            "ages": [30, 29, 1, 3]
        }
        """;

        mockMvc.perform(post("/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.searchId").value("abc-123"));
    }
}
