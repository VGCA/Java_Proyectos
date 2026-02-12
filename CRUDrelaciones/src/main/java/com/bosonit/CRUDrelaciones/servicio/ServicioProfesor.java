package com.bosonit.crudrelaciones.servicio;

import com.bosonit.crudrelaciones.interfazServicio.InterfazProfesor;
import com.bosonit.crudrelaciones.modelo.Profesor;
import com.bosonit.crudrelaciones.repositorio.RepoProfesores;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioProfesor implements InterfazProfesor {

    private final RepoProfesores repositorio;

    public ServicioProfesor(RepoProfesores repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Profesor> listarProfesores() {
        return (List<Profesor>) repositorio.findAll();
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
        repositorio.deleteById(id);
    }
}
