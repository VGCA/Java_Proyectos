package com.portfolio.ecommerce.application.service;

import com.portfolio.ecommerce.application.port.in.OrdenUseCase;
import com.portfolio.ecommerce.application.port.out.OrdenRepositoryPort;
import com.portfolio.ecommerce.application.port.out.UsuarioRepositoryPort;
import com.portfolio.ecommerce.domain.exception.RecursoNoEncontradoException;
import com.portfolio.ecommerce.domain.model.Carrito;
import com.portfolio.ecommerce.domain.model.Orden;
import com.portfolio.ecommerce.domain.model.Usuario;

import java.time.Clock;
import java.util.Date;
import java.util.List;

public class OrdenService implements OrdenUseCase {

    private final OrdenRepositoryPort ordenes;
    private final UsuarioRepositoryPort usuarios;
    private final Clock reloj;

    public OrdenService(OrdenRepositoryPort ordenes, UsuarioRepositoryPort usuarios, Clock reloj) {
        this.ordenes = ordenes;
        this.usuarios = usuarios;
        this.reloj = reloj;
    }

    @Override
    public Orden realizarPedido(Integer idUsuario, Carrito carrito) {
        if (carrito.estaVacio()) {
            throw new IllegalStateException("No se puede crear una orden con el carrito vacío");
        }
        Usuario usuario = usuarios.buscarPorId(idUsuario)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario", idUsuario));

        Orden orden = new Orden();
        orden.setUsuario(usuario);
        orden.setFechaCreacion(Date.from(reloj.instant()));
        orden.setNumero(generarNumeroOrden());
        orden.getDetalle().addAll(carrito.getLineas());
        orden.setTotal(carrito.total());
        return ordenes.guardar(orden);
    }

    @Override
    public List<Orden> listar() {
        return ordenes.buscarTodas();
    }

    @Override
    public List<Orden> listarDeUsuario(Integer idUsuario) {
        return ordenes.buscarPorUsuario(idUsuario);
    }

    @Override
    public Orden obtener(Integer id) {
        return ordenes.buscarPorId(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Orden", id));
    }

    @Override
    public Orden obtenerDeUsuario(Integer idOrden, Integer idUsuario) {
        Orden orden = obtener(idOrden);
        if (orden.getUsuario() == null || !idUsuario.equals(orden.getUsuario().getId())) {
            throw new RecursoNoEncontradoException("Orden", idOrden);
        }
        return orden;
    }

    /** Siguiente número correlativo con 10 dígitos: 0000000001, 0000000002... */
    String generarNumeroOrden() {
        int ultimo = ordenes.buscarTodas().stream()
                .mapToInt(o -> Integer.parseInt(o.getNumero()))
                .max()
                .orElse(0);
        return String.format("%010d", ultimo + 1);
    }
}
