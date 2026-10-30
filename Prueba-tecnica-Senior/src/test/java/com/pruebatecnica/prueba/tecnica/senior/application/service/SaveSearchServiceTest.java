package com.pruebatecnica.prueba.tecnica.senior.application.service;

import com.pruebatecnica.prueba.tecnica.senior.application.port.out.SearchRepositoryPort;
import com.pruebatecnica.prueba.tecnica.senior.domain.model.HotelSearch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SaveSearchServiceTest {

    @Mock
    private SearchRepositoryPort repository;

    @InjectMocks
    private SaveSearchService service;

    @Test
    void saveSearch_delegatesToRepositoryPort() {
        HotelSearch search = new HotelSearch("1", "h", "29/12/2023", "31/12/2023", List.of(30), 1);

        service.saveSearch(search);

        verify(repository).save(search);
    }
}
