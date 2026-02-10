package com.portfolio.ecommerce.servicio;

import com.portfolio.ecommerce.modelo.Usuario;
import com.portfolio.ecommerce.repositorio.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioServicio{

    private final UsuarioRepo usuarioRepo;

    public UsuarioServiceImp(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        return usuarioRepo.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepo.findByEmail(email);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepo.findAll();
    }
}
