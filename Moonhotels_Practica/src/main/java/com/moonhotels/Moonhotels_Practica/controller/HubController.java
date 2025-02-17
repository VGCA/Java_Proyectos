package com.moonhotels.Moonhotels_Practica.controller;

import com.moonhotels.Moonhotels_Practica.model.HubRequest;
import com.moonhotels.Moonhotels_Practica.model.HubResponse;
import com.moonhotels.Moonhotels_Practica.service.SearchService;
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
