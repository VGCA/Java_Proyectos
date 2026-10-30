package com.pruebatecnica.prueba_tecnica_tecdata.infrastructure.adapter.in.rest;

import com.pruebatecnica.prueba_tecnica_tecdata.application.port.in.GetApplicablePriceUseCase;
import com.pruebatecnica.prueba_tecnica_tecdata.infrastructure.adapter.in.rest.dto.PriceResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final GetApplicablePriceUseCase getApplicablePriceUseCase;

    public PriceController(GetApplicablePriceUseCase getApplicablePriceUseCase) {
        this.getApplicablePriceUseCase = getApplicablePriceUseCase;
    }

    @GetMapping
    public ResponseEntity<PriceResponse> getPrice(
            @RequestParam Integer productId,
            @RequestParam Integer brandId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {

        return getApplicablePriceUseCase.getApplicablePrice(productId, brandId, applicationDate)
                .map(PriceResponse::fromDomain)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
