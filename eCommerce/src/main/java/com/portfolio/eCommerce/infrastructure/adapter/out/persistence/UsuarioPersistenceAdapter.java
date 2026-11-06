package com.portfolio.ecommerce.infrastructure.adapter.out.persistence;

import com.portfolio.ecommerce.application.port.out.UsuarioRepositoryPort;
import com.portfolio.ecommerce.domain.model.Usuario;
import com.portfolio.ecommerce.infrastructure.adapter.out.persistence.repository.UsuarioJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional(readOnly = true)
public class UsuarioPersistenceAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository jpa;

    public UsuarioPersistenceAdapter(UsuarioJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    @Transactional
    public Usuario guardar(Usuario usuario) {
        return PersistenceMapper.aDominio(jpa.save(PersistenceMapper.aEntidad(usuario)));
    }

    @Override
    public Optional<Usuario> buscarPorId(Integer id) {
        return jpa.findById(id).map(PersistenceMapper::aDominio);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return jpa.findByEmail(email).map(PersistenceMapper::aDominio);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return jpa.findAll().stream().map(PersistenceMapper::aDominio).toList();
    }
}
