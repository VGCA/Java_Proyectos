package com.portfolio.ecommerce.infrastructure.adapter.out.persistence.repository;

import com.portfolio.ecommerce.infrastructure.adapter.out.persistence.entity.OrdenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdenJpaRepository extends JpaRepository<OrdenEntity, Integer> {

    List<OrdenEntity> findByUsuario_Id(Integer idUsuario);
}
