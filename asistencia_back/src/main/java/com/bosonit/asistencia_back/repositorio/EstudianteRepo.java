package com.bosonit.asistencia_back.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bosonit.asistencia_back.modelo.Estudiante;

import java.util.Optional;

public interface EstudianteRepo extends JpaRepository<Estudiante, Long> {

    Optional<Estudiante> findByCodigo(String codigo);

}
