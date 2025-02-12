package com.pruebatecnica.Prueba.tecnica.Senior.controller;

import com.pruebatecnica.Prueba.tecnica.Senior.entity.HotelSearch;
import com.pruebatecnica.Prueba.tecnica.Senior.repository.HotelSearchRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/count")
public class CountController {

    private final HotelSearchRepository repository;

    public CountController(HotelSearchRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public HotelSearch getSearchCount(@RequestParam String searchId) {
        Optional<HotelSearch> search = repository.findBySearchId(searchId);
        return search.orElseThrow(() -> new RuntimeException("Search not found"));
    }
}

