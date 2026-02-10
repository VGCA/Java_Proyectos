package com.portfolio.ecommerce.servicio;

import com.portfolio.ecommerce.modelo.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioServicio {

    Optional<Usuario> findById(Integer id);
    Usuario save(Usuario usuario);
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findAll();
}
