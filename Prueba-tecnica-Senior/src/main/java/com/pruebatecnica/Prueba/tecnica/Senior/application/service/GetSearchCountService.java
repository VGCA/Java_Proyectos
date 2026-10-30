package com.pruebatecnica.prueba.tecnica.senior.application.service;

import com.pruebatecnica.prueba.tecnica.senior.application.port.in.GetSearchCountUseCase;
import com.pruebatecnica.prueba.tecnica.senior.application.port.out.SearchRepositoryPort;
import com.pruebatecnica.prueba.tecnica.senior.domain.exception.SearchNotFoundException;
import com.pruebatecnica.prueba.tecnica.senior.domain.model.HotelSearch;

public class GetSearchCountService implements GetSearchCountUseCase {

    private final SearchRepositoryPort repository;

    public GetSearchCountService(SearchRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public HotelSearch getSearchCount(String searchId) {
        return repository.findBySearchId(searchId)
                .orElseThrow(SearchNotFoundException::new);
    }
}
