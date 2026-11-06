package com.portfolio.ecommerce.infrastructure.adapter.in.security;

import com.portfolio.ecommerce.application.port.in.UsuarioUseCase;
import com.portfolio.ecommerce.domain.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/** Adaptador: Spring Security pregunta por un usuario y nosotros respondemos vía un caso de uso. */
@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioUseCase usuarios;

    public UsuarioDetailsService(UsuarioUseCase usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario u = usuarios.buscarPorEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return new UsuarioPrincipal(u.getId(), u.getEmail(), u.getPassword(), u.getTipo());
    }
}
