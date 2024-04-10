package com.portfolio.eCommerce.servicio;

import com.portfolio.eCommerce.modelo.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioServicio {

    Optional<Usuario> findById(Integer id);
    Usuario save(Usuario usuario);
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findAll();
}
