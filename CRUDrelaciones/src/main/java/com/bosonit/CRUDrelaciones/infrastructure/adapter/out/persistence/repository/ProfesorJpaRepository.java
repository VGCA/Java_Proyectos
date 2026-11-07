package com.bosonit.crudrelaciones.infrastructure.adapter.out.persistence.repository;

import com.bosonit.crudrelaciones.infrastructure.adapter.out.persistence.entity.ProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorJpaRepository extends JpaRepository<ProfesorEntity, Integer> {
}
