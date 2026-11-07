package com.curso.dominio.modelo;

import java.util.ArrayList;
import java.util.List;

/** Agregado raiz: un pedido y sus detalles. Inmutable. */
public record Pedido(Integer id,
                     String codigo,
                     String fecha,
                     String estado,
                     Cliente cliente,
                     List<DetallePedido> detalles) {

    public Pedido {
        detalles = (detalles == null) ? List.of() : List.copyOf(detalles);
    }

    public Pedido conId(Integer nuevoId) {
        return new Pedido(nuevoId, codigo, fecha, estado, cliente, detalles);
    }

    public Pedido conCliente(Cliente nuevoCliente) {
        return new Pedido(id, codigo, fecha, estado, nuevoCliente, detalles);
    }

    public Pedido conDetalle(DetallePedido detalle) {
        // LN para anadir el detalle
        List<DetallePedido> nuevos = new ArrayList<>(detalles);
        nuevos.add(detalle);
        return new Pedido(id, codigo, fecha, estado, cliente, nuevos);
    }

}
