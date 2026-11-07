package com.bosonit.crudrelaciones.domain.port.in;

import com.bosonit.crudrelaciones.domain.model.Profesor;

import java.util.List;
import java.util.Optional;

public interface ProfesorUseCase {
    List<Profesor> listarProfesores();

    Optional<Profesor> listarPorId(Integer id);

    Profesor guardarProfesor(Profesor profesor);

    void borrarProfesor(Integer id);
}
