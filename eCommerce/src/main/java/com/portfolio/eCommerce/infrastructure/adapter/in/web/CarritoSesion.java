package com.portfolio.ecommerce.infrastructure.adapter.in.web;

import com.portfolio.ecommerce.domain.model.Carrito;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Un carrito POR SESIÓN HTTP. En el código original el carrito era un campo del
 * controlador (singleton) y por tanto compartido por todos los usuarios.
 */
@Component
@SessionScope
public class CarritoSesion {

    private final Carrito carrito = new Carrito();

    public Carrito get() {
        return carrito;
    }
}
