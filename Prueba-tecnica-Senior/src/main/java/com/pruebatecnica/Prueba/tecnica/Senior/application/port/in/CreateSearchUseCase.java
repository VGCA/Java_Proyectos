package com.pruebatecnica.prueba.tecnica.senior.application.port.in;

/** Driving port: "create a search and give me its id". */
public interface CreateSearchUseCase {
    String createSearch(CreateSearchCommand command);
}
