package com.pruebatecnica.prueba_tecnica_tecdata.infrastructure.adapter.in.rest.dto;

import com.pruebatecnica.prueba_tecnica_tecdata.domain.model.Price;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/** HTTP contract. Decoupled from the domain so that API changes don't leak into the core. */
public record PriceResponse(
        Integer brandId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer priceList,
        Integer productId,
        Integer priority,
        BigDecimal price,
        String currency) {

    public static PriceResponse fromDomain(Price p) {
        return new PriceResponse(p.brandId(), p.startDate(), p.endDate(), p.priceList(),
                p.productId(), p.priority(), p.price(), p.currency());
    }
}
