package com.bosonit.crudrelaciones.domain.port.out;

import com.bosonit.crudrelaciones.domain.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepositoryPort {
    List<Student> findAll();

    Optional<Student> findById(Integer id);

    Student save(Student student);

    void deleteById(Integer id);
}
