package com.bosonit.crudrelaciones.interfazservicio;

import com.bosonit.crudrelaciones.modelo.Profesor;

import java.util.List;
import java.util.Optional;

public interface InterfazProfesor {
    List<Profesor> listarProfesores();
    Optional<Profesor> listarPorId(String id);
    int guardarProfesor(Profesor profesor);
    void borrarProfesor(String id);
}
