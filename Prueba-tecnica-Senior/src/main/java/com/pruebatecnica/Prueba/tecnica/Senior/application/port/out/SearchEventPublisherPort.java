package com.pruebatecnica.prueba.tecnica.senior.application.port.out;

import com.pruebatecnica.prueba.tecnica.senior.domain.model.HotelSearch;

/** Driven port: what the application needs from "some message broker". */
public interface SearchEventPublisherPort {
    void publish(HotelSearch search);
}
