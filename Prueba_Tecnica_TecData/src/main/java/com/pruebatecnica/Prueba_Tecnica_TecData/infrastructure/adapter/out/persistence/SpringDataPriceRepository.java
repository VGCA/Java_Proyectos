package com.pruebatecnica.prueba_tecnica_tecdata.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

/** Spring Data detail. Package-private: nothing outside the adapter should depend on it. */
interface SpringDataPriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("""
            SELECT p FROM PriceEntity p
            WHERE p.productId = :productId
              AND p.brandId = :brandId
              AND :applicationDate BETWEEN p.startDate AND p.endDate
            """)
    List<PriceEntity> findApplicable(@Param("productId") Integer productId,
                                     @Param("brandId") Integer brandId,
                                     @Param("applicationDate") LocalDateTime applicationDate);
}
