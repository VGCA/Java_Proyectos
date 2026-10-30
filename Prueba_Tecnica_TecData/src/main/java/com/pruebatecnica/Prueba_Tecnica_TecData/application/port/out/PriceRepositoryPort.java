package com.pruebatecnica.prueba_tecnica_tecdata.application.port.out;

import com.pruebatecnica.prueba_tecnica_tecdata.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

/** Outbound (driven) port: what the application needs from the outside world to do its job. */
public interface PriceRepositoryPort {

    /** Returns every price of the product/brand whose date range contains the given date. */
    List<Price> findApplicablePrices(Integer productId, Integer brandId, LocalDateTime applicationDate);
}
