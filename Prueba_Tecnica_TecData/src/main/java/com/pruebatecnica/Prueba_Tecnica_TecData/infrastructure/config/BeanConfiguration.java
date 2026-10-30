package com.pruebatecnica.prueba_tecnica_tecdata.infrastructure.config;

import com.pruebatecnica.prueba_tecnica_tecdata.application.port.in.GetApplicablePriceUseCase;
import com.pruebatecnica.prueba_tecnica_tecdata.application.port.out.PriceRepositoryPort;
import com.pruebatecnica.prueba_tecnica_tecdata.application.service.GetApplicablePriceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Composition root: the only place that knows which implementation fulfils each port. */
@Configuration
public class BeanConfiguration {

    @Bean
    GetApplicablePriceUseCase getApplicablePriceUseCase(PriceRepositoryPort priceRepositoryPort) {
        return new GetApplicablePriceService(priceRepositoryPort);
    }
}
