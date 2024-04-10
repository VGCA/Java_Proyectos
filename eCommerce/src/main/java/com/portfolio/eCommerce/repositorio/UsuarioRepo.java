package com.portfolio.eCommerce.repositorio;

import com.portfolio.eCommerce.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario,Integer> {
    Optional<Usuario> findByEmail(String email);
}
