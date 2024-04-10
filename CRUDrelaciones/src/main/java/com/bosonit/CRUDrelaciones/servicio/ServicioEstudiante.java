package com.bosonit.CRUDrelaciones.servicio;

import com.bosonit.CRUDrelaciones.interfazServicio.InterfazStudent;
import com.bosonit.CRUDrelaciones.modelo.Student;
import com.bosonit.CRUDrelaciones.repositorio.Repositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioEstudiante implements InterfazStudent {

    @Autowired
    private Repositorio repositorio;

    @Override
    public List<Student> listarAlumnos() {
        return (List<Student>)repositorio.findAll();
    }

    @Override
    public Optional<Student> listarPorId(String id) {
        return repositorio.findById(id);
    }

    @Override
    public int guardarEstudiante(Student estudiante) {
        int respuesta = 0;
        Student nuevoEstudiante = repositorio.save(estudiante);
        if(!estudiante.equals(null)){
            respuesta = 1;
        }
        return respuesta;
    }

    @Override
    public void borrarEstudiante(String id) {
        repositorio.deleteById(id);
    }
}
