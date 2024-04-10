package com.bosonit.CRUDrelaciones.servicio;

import com.bosonit.CRUDrelaciones.interfazServicio.InterfazProfesor;
import com.bosonit.CRUDrelaciones.modelo.Profesor;
import com.bosonit.CRUDrelaciones.modelo.Student;
import com.bosonit.CRUDrelaciones.repositorio.RepoProfesores;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ServicioProfesor implements InterfazProfesor {

    @Autowired
    private RepoProfesores repositorio;

    @Override
    public List<Profesor> listarProfesores() {
        return (List<Profesor>)repositorio.findAll();
        // No puede usar el mismo repositorio que el de Students
    }

    @Override
    public Optional<Profesor> listarPorId(String id) {
        return Optional.empty();
    }

    @Override
    public int guardarProfesor(Profesor profesor) {
        return 0;
    }

    @Override
    public void borrarProfesor(String id) {

    }
}
