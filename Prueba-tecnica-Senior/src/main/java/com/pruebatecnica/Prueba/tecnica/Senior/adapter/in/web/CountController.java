package com.pruebatecnica.prueba.tecnica.senior.adapter.in.web;

import com.pruebatecnica.prueba.tecnica.senior.adapter.in.web.dto.HotelSearchResponse;
import com.pruebatecnica.prueba.tecnica.senior.application.port.in.GetSearchCountUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/count")
public class CountController {

    private final GetSearchCountUseCase getSearchCountUseCase;

    public CountController(GetSearchCountUseCase getSearchCountUseCase) {
        this.getSearchCountUseCase = getSearchCountUseCase;
    }

    @GetMapping
    public HotelSearchResponse getSearchCount(@RequestParam String searchId) {
        return HotelSearchResponse.from(getSearchCountUseCase.getSearchCount(searchId));
    }
}
