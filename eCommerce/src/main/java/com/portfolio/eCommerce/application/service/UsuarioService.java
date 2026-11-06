package com.portfolio.ecommerce.application.service;

import com.portfolio.ecommerce.application.port.in.UsuarioUseCase;
import com.portfolio.ecommerce.application.port.out.CodificadorPasswordPort;
import com.portfolio.ecommerce.application.port.out.UsuarioRepositoryPort;
import com.portfolio.ecommerce.domain.exception.EmailYaRegistradoException;
import com.portfolio.ecommerce.domain.exception.RecursoNoEncontradoException;
import com.portfolio.ecommerce.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public class UsuarioService implements UsuarioUseCase {

    private final UsuarioRepositoryPort usuarios;
    private final CodificadorPasswordPort codificador;

    public UsuarioService(UsuarioRepositoryPort usuarios, CodificadorPasswordPort codificador) {
        this.usuarios = usuarios;
        this.codificador = codificador;
    }

    @Override
    public Usuario registrar(DatosRegistro datos) {
        if (usuarios.buscarPorEmail(datos.email()).isPresent()) {
            throw new EmailYaRegistradoException(datos.email());
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(datos.nombre());
        usuario.setUsername(datos.username());
        usuario.setEmail(datos.email());
        usuario.setDireccion(datos.direccion());
        usuario.setTelefono(datos.telefono());
        usuario.setTipo(Usuario.ROL_USER);
        usuario.setPassword(codificador.codificar(datos.password()));
        return usuarios.guardar(usuario);
    }

    @Override
    public Usuario obtener(Integer id) {
        return usuarios.buscarPorId(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario", id));
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarios.buscarPorEmail(email);
    }

    @Override
    public List<Usuario> listar() {
        return usuarios.buscarTodos();
    }
}
