package com.pruebatecnica.prueba.tecnica.senior.application.service;

import com.pruebatecnica.prueba.tecnica.senior.application.port.in.SaveSearchUseCase;
import com.pruebatecnica.prueba.tecnica.senior.application.port.out.SearchRepositoryPort;
import com.pruebatecnica.prueba.tecnica.senior.domain.model.HotelSearch;

public class SaveSearchService implements SaveSearchUseCase {

    private final SearchRepositoryPort repository;

    public SaveSearchService(SearchRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public void saveSearch(HotelSearch search) {
        repository.save(search);
    }
}
