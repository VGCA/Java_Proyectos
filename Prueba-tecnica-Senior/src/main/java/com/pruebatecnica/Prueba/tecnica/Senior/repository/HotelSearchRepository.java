package com.pruebatecnica.Prueba.tecnica.Senior.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pruebatecnica.Prueba.tecnica.Senior.entity.HotelSearch;

import java.util.Optional;

@Repository
public interface HotelSearchRepository extends JpaRepository<HotelSearch, String> {
    Optional<HotelSearch> findBySearchId(String searchId);
}
