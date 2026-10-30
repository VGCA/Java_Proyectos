package com.pruebatecnica.prueba_tecnica_tecdata.application.port.in;

import com.pruebatecnica.prueba_tecnica_tecdata.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

/** Inbound (driving) port: what the outside world can ask this application to do. */
public interface GetApplicablePriceUseCase {

    Optional<Price> getApplicablePrice(Integer productId, Integer brandId, LocalDateTime applicationDate);
}
