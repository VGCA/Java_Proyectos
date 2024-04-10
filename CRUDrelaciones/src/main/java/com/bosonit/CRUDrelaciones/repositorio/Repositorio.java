package com.bosonit.CRUDrelaciones.repositorio;

import com.bosonit.CRUDrelaciones.modelo.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repositorio extends CrudRepository<Student,String> {
}
