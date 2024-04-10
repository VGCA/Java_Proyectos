package com.bosonit.CRUDrelaciones.interfazServicio;

import com.bosonit.CRUDrelaciones.modelo.Student;

import java.util.List;
import java.util.Optional;

public interface InterfazStudent {
    public List<Student> listarAlumnos();
    public Optional<Student> listarPorId(String id);
    public int guardarEstudiante(Student estudiante);
    public void borrarEstudiante(String id);
}
