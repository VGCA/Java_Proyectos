package com.portfolio.ecommerce.application.port.in;

import com.portfolio.ecommerce.domain.model.Carrito;
import com.portfolio.ecommerce.domain.model.Orden;

import java.util.List;

public interface OrdenUseCase {

    Orden realizarPedido(Integer idUsuario, Carrito carrito);

    List<Orden> listar();

    List<Orden> listarDeUsuario(Integer idUsuario);

    /** Para administradores: cualquier orden. */
    Orden obtener(Integer id);

    /** Para clientes: solo si la orden es suya (si no, se comporta como "no existe"). */
    Orden obtenerDeUsuario(Integer idOrden, Integer idUsuario);
}
