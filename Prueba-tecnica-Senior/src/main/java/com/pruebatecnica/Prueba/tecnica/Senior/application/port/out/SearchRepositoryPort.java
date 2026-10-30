package com.pruebatecnica.prueba.tecnica.senior.application.port.out;

import com.pruebatecnica.prueba.tecnica.senior.domain.model.HotelSearch;

import java.util.Optional;

/** Driven port: what the application needs from "some storage". */
public interface SearchRepositoryPort {
    HotelSearch save(HotelSearch search);

    Optional<HotelSearch> findBySearchId(String searchId);
}
