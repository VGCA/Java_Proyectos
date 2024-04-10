package com.bosonit.csvh2.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bosonit.csvh2.modelo.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
}
