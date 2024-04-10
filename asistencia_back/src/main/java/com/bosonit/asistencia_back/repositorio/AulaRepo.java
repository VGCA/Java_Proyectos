package com.bosonit.asistencia_back.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bosonit.asistencia_back.modelo.Aula;

public interface AulaRepo extends JpaRepository<Aula, Long> {
}
