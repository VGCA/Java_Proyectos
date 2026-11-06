package com.portfolio.ecommerce.infrastructure.adapter.in.web.form;

import com.portfolio.ecommerce.application.port.in.ProductoUseCase.DatosProducto;

/** Datos del formulario HTML de productos (capa web, no es un objeto de dominio). */
public record ProductoForm(Integer id, String nombre, String descripcion, Double precio, Integer cantidad) {

    public DatosProducto aDatos() {
        return new DatosProducto(nombre, descripcion,
                precio == null ? 0 : precio, cantidad == null ? 0 : cantidad);
    }
}
