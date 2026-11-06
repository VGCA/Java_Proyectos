package com.portfolio.ecommerce.infrastructure.adapter.in.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

/** Usuario autenticado de Spring Security, con el id de nuestro usuario de dominio. */
public class UsuarioPrincipal extends User {

    private final Integer id;

    public UsuarioPrincipal(Integer id, String email, String passwordCodificado, String rol) {
        super(email, passwordCodificado, AuthorityUtils.createAuthorityList("ROLE_" + rol));
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
