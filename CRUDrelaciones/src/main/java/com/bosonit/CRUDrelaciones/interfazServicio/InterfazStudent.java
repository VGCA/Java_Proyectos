package com.bosonit.crudrelaciones.interfazservicio;

import com.bosonit.crudrelaciones.modelo.Student;

import java.util.List;
import java.util.Optional;

public interface InterfazStudent {
    List<Student> listarAlumnos();
    Optional<Student> listarPorId(Integer id);
    Student guardarEstudiante(Student estudiante);
    void borrarEstudiante(Integer id);
}
