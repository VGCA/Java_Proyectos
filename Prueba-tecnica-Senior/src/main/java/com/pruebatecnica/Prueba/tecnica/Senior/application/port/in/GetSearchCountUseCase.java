package com.pruebatecnica.prueba.tecnica.senior.application.port.in;

import com.pruebatecnica.prueba.tecnica.senior.domain.model.HotelSearch;

/** Driving port: "give me a stored search with its count". */
public interface GetSearchCountUseCase {
    HotelSearch getSearchCount(String searchId);
}
