package com.pruebatecnica.prueba.tecnica.senior.adapter.in.web;

import com.pruebatecnica.prueba.tecnica.senior.adapter.in.web.dto.HotelSearchResponse;
import com.pruebatecnica.prueba.tecnica.senior.application.port.in.GetSearchCountUseCase;
import com.pruebatecnica.prueba.tecnica.senior.domain.exception.SearchNotFoundException;
import com.pruebatecnica.prueba.tecnica.senior.domain.model.HotelSearch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CountControllerTest {

    @Mock
    private GetSearchCountUseCase useCase;

    @InjectMocks
    private CountController controller;

    @Test
    void getSearchCount_success() {
        when(useCase.getSearchCount("1234")).thenReturn(new HotelSearch("1234", "1234aBc",
                "29/12/2023", "31/12/2023", List.of(30, 29, 1, 3), 100));

        HotelSearchResponse result = controller.getSearchCount("1234");

        assertEquals("1234", result.searchId());
        assertEquals("1234aBc", result.hotelId());
        assertEquals(100, result.count());
    }

    @Test
    void getSearchCount_notFound() {
        when(useCase.getSearchCount("9999")).thenThrow(new SearchNotFoundException());

        assertThrows(SearchNotFoundException.class, () -> controller.getSearchCount("9999"));
    }
}
