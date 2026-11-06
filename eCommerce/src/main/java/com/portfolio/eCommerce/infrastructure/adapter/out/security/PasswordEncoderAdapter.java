package com.portfolio.ecommerce.infrastructure.adapter.out.security;

import com.portfolio.ecommerce.application.port.out.CodificadorPasswordPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderAdapter implements CodificadorPasswordPort {

    private final PasswordEncoder encoder;

    public PasswordEncoderAdapter(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public String codificar(String passwordPlano) {
        return encoder.encode(passwordPlano);
    }
}
