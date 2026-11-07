package com.bosonit.crudrelaciones.infrastructure.adapter.out.persistence;

import com.bosonit.crudrelaciones.domain.model.Student;
import com.bosonit.crudrelaciones.domain.port.out.StudentRepositoryPort;
import com.bosonit.crudrelaciones.infrastructure.adapter.out.persistence.mapper.StudentMapper;
import com.bosonit.crudrelaciones.infrastructure.adapter.out.persistence.repository.StudentJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StudentPersistenceAdapter implements StudentRepositoryPort {

    private final StudentJpaRepository jpaRepository;

    public StudentPersistenceAdapter(StudentJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Student> findAll() {
        return jpaRepository.findAll().stream().map(StudentMapper::toDomain).toList();
    }

    @Override
    public Optional<Student> findById(Integer id) {
        return jpaRepository.findById(id).map(StudentMapper::toDomain);
    }

    @Override
    public Student save(Student student) {
        return StudentMapper.toDomain(jpaRepository.save(StudentMapper.toEntity(student)));
    }

    @Override
    public void deleteById(Integer id) {
        jpaRepository.deleteById(id);
    }
}
