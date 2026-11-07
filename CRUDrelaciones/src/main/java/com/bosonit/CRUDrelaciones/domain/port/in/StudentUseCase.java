package com.bosonit.crudrelaciones.domain.port.in;

import com.bosonit.crudrelaciones.domain.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentUseCase {
    List<Student> listarAlumnos();

    Optional<Student> listarPorId(Integer id);

    Student guardarEstudiante(Student estudiante);

    void borrarEstudiante(Integer id);
}
