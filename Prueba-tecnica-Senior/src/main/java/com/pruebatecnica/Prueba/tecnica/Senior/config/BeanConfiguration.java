package com.pruebatecnica.prueba.tecnica.senior.config;

import com.pruebatecnica.prueba.tecnica.senior.application.port.in.CreateSearchUseCase;
import com.pruebatecnica.prueba.tecnica.senior.application.port.in.GetSearchCountUseCase;
import com.pruebatecnica.prueba.tecnica.senior.application.port.in.SaveSearchUseCase;
import com.pruebatecnica.prueba.tecnica.senior.application.port.out.SearchRepositoryPort;
import com.pruebatecnica.prueba.tecnica.senior.application.service.CreateSearchService;
import com.pruebatecnica.prueba.tecnica.senior.application.service.GetSearchCountService;
import com.pruebatecnica.prueba.tecnica.senior.application.service.SaveSearchService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Composition root: the only place that knows which implementation goes with which port.
 * The application layer has no Spring annotations, so it is wired here by hand.
 */
@Configuration
public class BeanConfiguration {

    @Bean
    CreateSearchUseCase createSearchUseCase() {
        return new CreateSearchService();
    }

    @Bean
    GetSearchCountUseCase getSearchCountUseCase(SearchRepositoryPort repository) {
        return new GetSearchCountService(repository);
    }

    @Bean
    SaveSearchUseCase saveSearchUseCase(SearchRepositoryPort repository) {
        return new SaveSearchService(repository);
    }
}
