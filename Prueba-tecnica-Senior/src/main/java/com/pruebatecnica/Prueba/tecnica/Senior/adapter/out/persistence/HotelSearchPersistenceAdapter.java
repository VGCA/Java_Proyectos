package com.pruebatecnica.prueba.tecnica.senior.adapter.out.persistence;

import com.pruebatecnica.prueba.tecnica.senior.application.port.out.SearchRepositoryPort;
import com.pruebatecnica.prueba.tecnica.senior.domain.model.HotelSearch;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HotelSearchPersistenceAdapter implements SearchRepositoryPort {

    private final SpringDataHotelSearchRepository repository;

    HotelSearchPersistenceAdapter(SpringDataHotelSearchRepository repository) {
        this.repository = repository;
    }

    @Override
    public HotelSearch save(HotelSearch search) {
        return toDomain(repository.save(toEntity(search)));
    }

    @Override
    public Optional<HotelSearch> findBySearchId(String searchId) {
        return repository.findBySearchId(searchId).map(HotelSearchPersistenceAdapter::toDomain);
    }

    private static HotelSearchJpaEntity toEntity(HotelSearch s) {
        return new HotelSearchJpaEntity(s.getSearchId(), s.getHotelId(), s.getCheckIn(),
                s.getCheckOut(), s.getAges(), s.getCount());
    }

    private static HotelSearch toDomain(HotelSearchJpaEntity e) {
        return new HotelSearch(e.getSearchId(), e.getHotelId(), e.getCheckIn(),
                e.getCheckOut(), e.getAges(), e.getCount());
    }
}
