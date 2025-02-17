package com.moonhotels.Moonhotels_Practica.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moonhotels.Moonhotels_Practica.controller.HubController;
import com.moonhotels.Moonhotels_Practica.model.HubRequest;
import com.moonhotels.Moonhotels_Practica.service.SearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.LocalDate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HubControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private SearchService searchService;

    @InjectMocks
    private HubController hubController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(hubController).build();

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void shouldReturn200ForValidSearchRequest() throws Exception {

        HubRequest request = new HubRequest();
        request.setHotelId(1);
        request.setCheckIn(LocalDate.of(2025, 5, 1));
        request.setCheckOut(LocalDate.of(2025, 5, 6));
        request.setNumberOfGuests(2);
        request.setNumberOfRooms(1);
        request.setCurrency("USD");

        when(searchService.search(any())).thenReturn(new com.moonhotels.Moonhotels_Practica.model.HubResponse());

        mockMvc.perform(post("/hub/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(searchService, times(1)).search(any());
    }
}


