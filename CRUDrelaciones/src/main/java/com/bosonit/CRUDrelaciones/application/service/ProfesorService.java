package com.bosonit.crudrelaciones.application.service;

import com.bosonit.crudrelaciones.domain.model.Profesor;
import com.bosonit.crudrelaciones.domain.port.in.ProfesorUseCase;
import com.bosonit.crudrelaciones.domain.port.out.ProfesorRepositoryPort;

import java.util.List;
import java.util.Optional;

public class ProfesorService implements ProfesorUseCase {

    private final ProfesorRepositoryPort repositorio;

    public ProfesorService(ProfesorRepositoryPort repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Profesor> listarProfesores() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Profesor> listarPorId(Integer id) {
        return repositorio.findById(id);
    }

    @Override
    public Profesor guardarProfesor(Profesor profesor) {
        return repositorio.save(profesor);
    }

    @Override
    public void borrarProfesor(Integer id) {
        repositorio.deleteById(id);
    }
}
