package com.moonhotels.moonhotels_practica.controller;

import com.moonhotels.moonhotels_practica.model.HubRequest;
import com.moonhotels.moonhotels_practica.model.HubResponse;
import com.moonhotels.moonhotels_practica.service.SearchService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hub")
public class HubController {

    private final SearchService searchService;

    public HubController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/search")
    public HubResponse search(@RequestBody HubRequest request) {
        return searchService.search(request);
    }
}
