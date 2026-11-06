package com.portfolio.ecommerce.infrastructure.adapter.in.web.form;

import com.portfolio.ecommerce.application.port.in.UsuarioUseCase.DatosRegistro;

public record UsuarioForm(String nombre, String username, String email,
                          String direccion, String telefono, String password) {

    public DatosRegistro aDatos() {
        return new DatosRegistro(nombre, username, email, direccion, telefono, password);
    }
}
