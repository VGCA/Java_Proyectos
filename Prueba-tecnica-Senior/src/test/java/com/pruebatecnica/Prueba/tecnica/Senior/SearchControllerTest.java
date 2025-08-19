package com.pruebatecnica.Prueba.tecnica.Senior;

import com.pruebatecnica.Prueba.tecnica.Senior.controller.SearchController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SearchController.class)
class SearchControllerTest {

    private final MockMvc mockMvc;

    @Autowired
    public SearchControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void testCreateSearch() throws Exception {
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
                .andExpect(status().isOk());
    }
}

