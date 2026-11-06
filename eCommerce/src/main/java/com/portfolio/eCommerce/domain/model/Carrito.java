package com.portfolio.ecommerce.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Carrito de la compra. Contiene las reglas de negocio del carrito (no duplicar
 * productos, calcular el total) sin depender de ningún framework.
 */
public class Carrito {

    private final List<DetalleOrden> lineas = new ArrayList<>();

    /** Añade el producto; si ya estaba en el carrito no hace nada (regla original). */
    public void agregar(Producto producto, int cantidad) {
        if (!contiene(producto.getId())) {
            lineas.add(DetalleOrden.desdeProducto(producto, cantidad));
        }
    }

    public void quitar(Integer idProducto) {
        lineas.removeIf(l -> Objects.equals(l.getProducto().getId(), idProducto));
    }

    public boolean contiene(Integer idProducto) {
        return lineas.stream().anyMatch(l -> Objects.equals(l.getProducto().getId(), idProducto));
    }

    public double total() {
        return lineas.stream().mapToDouble(DetalleOrden::getTotal).sum();
    }

    public boolean estaVacio() {
        return lineas.isEmpty();
    }

    public void vaciar() {
        lineas.clear();
    }

    public List<DetalleOrden> getLineas() {
        return Collections.unmodifiableList(lineas);
    }
}
