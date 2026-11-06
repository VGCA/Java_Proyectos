package com.portfolio.ecommerce.infrastructure.adapter.out.persistence.repository;

import com.portfolio.ecommerce.infrastructure.adapter.out.persistence.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoJpaRepository extends JpaRepository<ProductoEntity, Integer> {
}
