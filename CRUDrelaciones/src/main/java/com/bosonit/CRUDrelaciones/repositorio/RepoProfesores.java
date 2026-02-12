package com.bosonit.crudrelaciones.repositorio;

import com.bosonit.crudrelaciones.modelo.Profesor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoProfesores extends CrudRepository<Profesor, String> {
}
