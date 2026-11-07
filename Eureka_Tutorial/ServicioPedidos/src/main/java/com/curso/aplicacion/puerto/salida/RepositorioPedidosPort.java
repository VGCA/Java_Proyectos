package com.curso.aplicacion.puerto.salida;

import java.util.List;
import java.util.Optional;

import com.curso.dominio.modelo.Pedido;

public interface RepositorioPedidosPort {

    Pedido guardar(Pedido pedido);

    Optional<Pedido> buscarPorId(Integer id);

    List<Pedido> buscarTodos();

}
