package com.pruebatecnica.Prueba.tecnica.Senior;

import com.pruebatecnica.Prueba.tecnica.Senior.controller.CountController;
import com.pruebatecnica.Prueba.tecnica.Senior.entity.HotelSearch;
import com.pruebatecnica.Prueba.tecnica.Senior.repository.HotelSearchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CountControllerTest {

    @Mock
    private HotelSearchRepository repository;

    @InjectMocks
    private CountController countController;

    @Test
    void testGetSearchCount_Success() {

        HotelSearch search = new HotelSearch("1234",
                "1234aBc",
                "29/12/2023",
                "31/12/2023",
                List.of(30, 29, 1, 3),
                100);


        when(repository.findBySearchId("1234")).thenReturn(Optional.of(search));


        HotelSearch result = countController.getSearchCount("1234");

        assertNotNull(result);
        assertEquals("1234", result.getSearchId());
        assertEquals("1234aBc", result.getHotelId());
        assertEquals(100, result.getCount());


        verify(repository, times(1)).findBySearchId("1234");
    }

    @Test
    void testGetSearchCount_NotFound() {

        when(repository.findBySearchId("9999")).thenReturn(Optional.empty());


        Exception exception = assertThrows(RuntimeException.class, () ->
            countController.getSearchCount("9999")
        );

        assertEquals("Search not found", exception.getMessage());

        verify(repository, times(1)).findBySearchId("9999");
    }
}

