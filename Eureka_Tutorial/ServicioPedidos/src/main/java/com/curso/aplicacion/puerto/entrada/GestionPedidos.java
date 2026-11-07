package com.curso.aplicacion.puerto.entrada;

import java.util.List;

import com.curso.dominio.modelo.Pedido;

/** Puerto de entrada: casos de uso que ofrece la aplicacion. */
public interface GestionPedidos {

    Pedido altaPedido(Pedido pedido);

    List<Pedido> listarPedidos();

    Pedido buscarPedido(Integer id);

    void modificarPedido(Pedido pedido);

    void borrarPedido(Integer idPedido);

}
