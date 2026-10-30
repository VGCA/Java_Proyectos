package com.pruebatecnica.prueba_tecnica_tecdata.infrastructure.adapter.out.persistence;

import com.pruebatecnica.prueba_tecnica_tecdata.application.port.out.PriceRepositoryPort;
import com.pruebatecnica.prueba_tecnica_tecdata.domain.model.Price;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/** Implements the outbound port using Spring Data JPA and translates entity <-> domain. */
@Component
class PricePersistenceAdapter implements PriceRepositoryPort {

    private final SpringDataPriceRepository repository;

    PricePersistenceAdapter(SpringDataPriceRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Price> findApplicablePrices(Integer productId, Integer brandId, LocalDateTime applicationDate) {
        return repository.findApplicable(productId, brandId, applicationDate)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    private Price toDomain(PriceEntity e) {
        return new Price(e.getBrandId(), e.getStartDate(), e.getEndDate(), e.getPriceList(),
                e.getProductId(), e.getPriority(), e.getPrice(), e.getCurrency());
    }
}
