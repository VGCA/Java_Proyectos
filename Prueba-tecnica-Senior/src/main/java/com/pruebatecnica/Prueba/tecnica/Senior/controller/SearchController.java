package com.pruebatecnica.Prueba.tecnica.Senior.controller;

import com.pruebatecnica.Prueba.tecnica.Senior.dto.SearchRequest;
import com.pruebatecnica.Prueba.tecnica.Senior.dto.SearchResponse;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/search")
public class SearchController {

    @PostMapping
    public SearchResponse createSearch(@RequestBody SearchRequest request) {
        String searchId = UUID.randomUUID().toString();
        return new SearchResponse(searchId);
    }
}

