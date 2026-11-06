package com.portfolio.ecommerce.infrastructure.adapter.out.persistence.repository;

import com.portfolio.ecommerce.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Integer> {

    Optional<UsuarioEntity> findByEmail(String email);
}
