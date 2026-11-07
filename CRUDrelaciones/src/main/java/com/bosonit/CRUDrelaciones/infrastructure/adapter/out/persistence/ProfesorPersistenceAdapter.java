package com.bosonit.crudrelaciones.infrastructure.adapter.out.persistence;

import com.bosonit.crudrelaciones.domain.model.Profesor;
import com.bosonit.crudrelaciones.domain.port.out.ProfesorRepositoryPort;
import com.bosonit.crudrelaciones.infrastructure.adapter.out.persistence.mapper.ProfesorMapper;
import com.bosonit.crudrelaciones.infrastructure.adapter.out.persistence.repository.ProfesorJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProfesorPersistenceAdapter implements ProfesorRepositoryPort {

    private final ProfesorJpaRepository jpaRepository;

    public ProfesorPersistenceAdapter(ProfesorJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Profesor> findAll() {
        return jpaRepository.findAll().stream().map(ProfesorMapper::toDomain).toList();
    }

    @Override
    public Optional<Profesor> findById(Integer id) {
        return jpaRepository.findById(id).map(ProfesorMapper::toDomain);
    }

    @Override
    public Profesor save(Profesor profesor) {
        return ProfesorMapper.toDomain(jpaRepository.save(ProfesorMapper.toEntity(profesor)));
    }

    @Override
    public void deleteById(Integer id) {
        jpaRepository.deleteById(id);
    }
}
