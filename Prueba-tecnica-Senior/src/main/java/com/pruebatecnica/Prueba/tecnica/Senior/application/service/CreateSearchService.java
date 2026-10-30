package com.pruebatecnica.prueba.tecnica.senior.application.service;

import com.pruebatecnica.prueba.tecnica.senior.application.port.in.CreateSearchCommand;
import com.pruebatecnica.prueba.tecnica.senior.application.port.in.CreateSearchUseCase;

import java.util.UUID;

/** Same behaviour as the old SearchController: generate and return a search id. */
public class CreateSearchService implements CreateSearchUseCase {

    @Override
    public String createSearch(CreateSearchCommand command) {
        return UUID.randomUUID().toString();
    }
}
