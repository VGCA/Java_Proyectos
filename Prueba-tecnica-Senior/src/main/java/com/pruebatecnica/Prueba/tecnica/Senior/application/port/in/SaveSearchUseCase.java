package com.pruebatecnica.prueba.tecnica.senior.application.port.in;

import com.pruebatecnica.prueba.tecnica.senior.domain.model.HotelSearch;

/** Driving port: "store this search" (used by the Kafka consumer). */
public interface SaveSearchUseCase {
    void saveSearch(HotelSearch search);
}
