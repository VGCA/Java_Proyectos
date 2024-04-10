package com.bosonit.asistencia_back.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bosonit.asistencia_back.modelo.Asistencia;

import java.time.LocalDate;
import java.util.Optional;

public interface AsistenciaRepo extends JpaRepository<Asistencia, Long> {

    Optional<Asistencia> findByEstudiante_CodigoAndFechaIngreso(String codigo, LocalDate fechaIngreso);
}
