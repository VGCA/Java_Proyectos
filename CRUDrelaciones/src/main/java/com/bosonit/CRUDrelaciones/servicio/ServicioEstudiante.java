package com.bosonit.crudrelaciones.servicio;

import com.bosonit.crudrelaciones.interfazservicio.InterfazStudent;
import com.bosonit.crudrelaciones.modelo.Student;
import com.bosonit.crudrelaciones.repositorio.Repositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioEstudiante implements InterfazStudent {

    private final Repositorio repositorio;

    public ServicioEstudiante(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Student> listarAlumnos() {
        return (List<Student>)repositorio.findAll();
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
