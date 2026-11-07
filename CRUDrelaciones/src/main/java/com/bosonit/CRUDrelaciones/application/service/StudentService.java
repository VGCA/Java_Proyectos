package com.bosonit.crudrelaciones.application.service;

import com.bosonit.crudrelaciones.domain.model.Student;
import com.bosonit.crudrelaciones.domain.port.in.StudentUseCase;
import com.bosonit.crudrelaciones.domain.port.out.StudentRepositoryPort;

import java.util.List;
import java.util.Optional;

public class StudentService implements StudentUseCase {

    private final StudentRepositoryPort repositorio;

    public StudentService(StudentRepositoryPort repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Student> listarAlumnos() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Student> listarPorId(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    public Student guardarEstudiante(Student estudiante) {
        return repositorio.save(estudiante);
    }

    @Override
    public void borrarEstudiante(Integer id) {
        repositorio.deleteById(id);
    }
}
