package com.bosonit.crudrelaciones.repositorio;

import com.bosonit.crudrelaciones.modelo.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repositorio extends CrudRepository<Student,Integer> {
}
