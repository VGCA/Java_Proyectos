package com.pruebatecnica.Prueba_Tecnica_TecData.service;

import com.pruebatecnica.Prueba_Tecnica_TecData.model.Price;
import com.pruebatecnica.Prueba_Tecnica_TecData.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public Optional<Price> getApplicablePrice(Integer productId, Integer brandId, LocalDateTime applicationDate) {
        List<Price> prices = priceRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                productId, brandId, applicationDate, applicationDate
        );

        return prices.stream().findFirst();
    }
}

