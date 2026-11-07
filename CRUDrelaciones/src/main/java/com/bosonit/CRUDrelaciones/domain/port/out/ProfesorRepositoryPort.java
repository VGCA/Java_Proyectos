package com.bosonit.crudrelaciones.domain.port.out;

import com.bosonit.crudrelaciones.domain.model.Profesor;

import java.util.List;
import java.util.Optional;

public interface ProfesorRepositoryPort {
    List<Profesor> findAll();

    Optional<Profesor> findById(Integer id);

    Profesor save(Profesor profesor);

    void deleteById(Integer id);
}
