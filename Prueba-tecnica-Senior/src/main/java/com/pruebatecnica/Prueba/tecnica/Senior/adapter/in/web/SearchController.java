package com.pruebatecnica.prueba.tecnica.senior.adapter.in.web;

import com.pruebatecnica.prueba.tecnica.senior.adapter.in.web.dto.SearchRequest;
import com.pruebatecnica.prueba.tecnica.senior.adapter.in.web.dto.SearchResponse;
import com.pruebatecnica.prueba.tecnica.senior.application.port.in.CreateSearchCommand;
import com.pruebatecnica.prueba.tecnica.senior.application.port.in.CreateSearchUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final CreateSearchUseCase createSearchUseCase;

    public SearchController(CreateSearchUseCase createSearchUseCase) {
        this.createSearchUseCase = createSearchUseCase;
    }

    @PostMapping
    public SearchResponse createSearch(@RequestBody SearchRequest request) {
        CreateSearchCommand command = new CreateSearchCommand(
                request.hotelId(), request.checkIn(), request.checkOut(), request.ages());
        return new SearchResponse(createSearchUseCase.createSearch(command));
    }
}
