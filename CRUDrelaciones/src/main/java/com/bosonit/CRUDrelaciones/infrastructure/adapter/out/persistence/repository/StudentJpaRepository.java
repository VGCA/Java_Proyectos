package com.bosonit.crudrelaciones.infrastructure.adapter.out.persistence.repository;

import com.bosonit.crudrelaciones.infrastructure.adapter.out.persistence.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentJpaRepository extends JpaRepository<StudentEntity, Integer> {
}
