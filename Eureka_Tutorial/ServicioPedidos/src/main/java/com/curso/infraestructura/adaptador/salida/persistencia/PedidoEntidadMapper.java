package com.curso.infraestructura.adaptador.salida.persistencia;

import java.util.ArrayList;
import java.util.List;

import com.curso.dominio.modelo.DetallePedido;
import com.curso.dominio.modelo.Pedido;
import com.curso.dominio.modelo.Producto;

import jakarta.persistence.EntityManager;

final class PedidoEntidadMapper {

    private PedidoEntidadMapper() {
    }

    static Pedido aDominio(PedidoEntidad e) {
        List<DetallePedido> detalles = new ArrayList<>();
        if (e.getDetalles() != null) {
            for (DetallePedidoEntidad d : e.getDetalles()) {
                detalles.add(new DetallePedido(d.getId(), aDominio(d.getProducto()),
                        d.getPrecio(), d.getCantidad()));
            }
        }
        return new Pedido(e.getId(), e.getCodigo(), e.getFecha(), e.getEstado(),
                e.getCliente() == null ? null : ClienteEntidadMapper.aDominio(e.getCliente()),
                detalles);
    }

    private static Producto aDominio(ProductoEntidad p) {
        if (p == null) {
            return null;
        }
        return new Producto(p.getId(), p.getNombre(), p.getFabricante(), p.getPrecio(), p.getExistencias());
    }

    /**
     * Las referencias al cliente y a los productos se resuelven con getReference: son entidades
     * que ya existen y que este agregado solo referencia (no es su dueno).
     */
    static PedidoEntidad aEntidad(Pedido p, EntityManager em) {
        PedidoEntidad e = new PedidoEntidad();
        e.setId(p.id());
        e.setCodigo(p.codigo());
        e.setFecha(p.fecha());
        e.setEstado(p.estado());
        if (p.cliente() != null) {
            e.setCliente(em.getReference(ClienteEntidad.class, p.cliente().login()));
        }
        List<DetallePedidoEntidad> detalles = new ArrayList<>();
        for (DetallePedido d : p.detalles()) {
            DetallePedidoEntidad de = new DetallePedidoEntidad();
            de.setId(d.id());
            de.setPedido(e);
            if (d.producto() != null && d.producto().id() != null) {
                de.setProducto(em.getReference(ProductoEntidad.class, d.producto().id()));
            }
            de.setPrecio(d.precio());
            de.setCantidad(d.cantidad());
            detalles.add(de);
        }
        e.setDetalles(detalles);
        return e;
    }

}
