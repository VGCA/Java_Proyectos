package com.pruebatecnica.prueba_tecnica_tecdata.application.service;

import com.pruebatecnica.prueba_tecnica_tecdata.application.port.in.GetApplicablePriceUseCase;
import com.pruebatecnica.prueba_tecnica_tecdata.application.port.out.PriceRepositoryPort;
import com.pruebatecnica.prueba_tecnica_tecdata.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

/**
 * Implements the use case. The business rule lives here (not in a repository method name):
 * when several prices overlap, the one with the highest priority wins.
 * No framework annotations: the wiring is done in infrastructure.config.BeanConfiguration.
 */
public class GetApplicablePriceService implements GetApplicablePriceUseCase {

    private final PriceRepositoryPort priceRepositoryPort;

    public GetApplicablePriceService(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    @Override
    public Optional<Price> getApplicablePrice(Integer productId, Integer brandId, LocalDateTime applicationDate) {
        return priceRepositoryPort.findApplicablePrices(productId, brandId, applicationDate)
                .stream()
                .max(Comparator.comparing(Price::priority));
    }
}
