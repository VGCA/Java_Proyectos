package com.curso.dominio.modelo;

/** Linea de un pedido. No conoce al pedido que la contiene: la relacion la gestiona el agregado Pedido. */
public record DetallePedido(Integer id,
                            Producto producto,
                            Double precio,
                            Integer cantidad) {
}
