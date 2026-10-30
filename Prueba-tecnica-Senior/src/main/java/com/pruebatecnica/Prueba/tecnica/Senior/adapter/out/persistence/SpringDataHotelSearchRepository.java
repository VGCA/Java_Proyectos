package com.pruebatecnica.prueba.tecnica.senior.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/** Spring Data detail: package-private, nobody outside this adapter needs to know it exists. */
interface SpringDataHotelSearchRepository extends JpaRepository<HotelSearchJpaEntity, String> {
    Optional<HotelSearchJpaEntity> findBySearchId(String searchId);
}
