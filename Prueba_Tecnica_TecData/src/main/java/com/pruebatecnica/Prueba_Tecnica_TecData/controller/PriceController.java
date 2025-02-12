package com.pruebatecnica.Prueba_Tecnica_TecData.controller;

import com.pruebatecnica.Prueba_Tecnica_TecData.model.Price;
import com.pruebatecnica.Prueba_Tecnica_TecData.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    @Autowired
    private PriceService priceService;

    @GetMapping
    public Optional<Price> getPrice(
            @RequestParam Integer productId,
            @RequestParam Integer brandId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {
        return priceService.getApplicablePrice(productId, brandId, applicationDate);
    }
}

