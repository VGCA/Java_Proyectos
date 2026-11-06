package com.portfolio.ecommerce.application.port.in;

import com.portfolio.ecommerce.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioUseCase {

    record DatosRegistro(String nombre, String username, String email,
                         String direccion, String telefono, String password) {
    }

    Usuario registrar(DatosRegistro datos);

    Usuario obtener(Integer id);

    Optional<Usuario> buscarPorEmail(String email);

    List<Usuario> listar();
}
