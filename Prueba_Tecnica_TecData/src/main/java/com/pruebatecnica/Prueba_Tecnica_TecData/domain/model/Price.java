package com.pruebatecnica.prueba_tecnica_tecdata.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Domain model: a price that applies to a product of a brand during a date range.
 * Pure Java: it knows nothing about Spring, JPA or HTTP.
 */
public record Price(
        Integer brandId,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer priceList,
        Integer productId,
        Integer priority,
        BigDecimal price,
        String currency) {

    public Price {
        Objects.requireNonNull(brandId, "brandId is required");
        Objects.requireNonNull(startDate, "startDate is required");
        Objects.requireNonNull(endDate, "endDate is required");
        Objects.requireNonNull(priceList, "priceList is required");
        Objects.requireNonNull(productId, "productId is required");
        Objects.requireNonNull(priority, "priority is required");
        Objects.requireNonNull(price, "price is required");
        Objects.requireNonNull(currency, "currency is required");
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("endDate cannot be before startDate");
        }
    }

    /** A price applies at a given moment if the date is inside [startDate, endDate]. */
    public boolean isApplicableAt(LocalDateTime date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}
