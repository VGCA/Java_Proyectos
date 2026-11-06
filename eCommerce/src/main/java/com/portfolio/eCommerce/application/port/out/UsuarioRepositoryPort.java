package com.portfolio.ecommerce.application.port.out;

import com.portfolio.ecommerce.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositoryPort {

    Usuario guardar(Usuario usuario);

    Optional<Usuario> buscarPorId(Integer id);

    Optional<Usuario> buscarPorEmail(String email);

    List<Usuario> buscarTodos();
}
