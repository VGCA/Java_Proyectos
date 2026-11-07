package com.curso.aplicacion.puerto.salida;

import java.util.List;
import java.util.Optional;

import com.curso.dominio.modelo.Cliente;

/**
 * Puerto de salida: lo que la aplicacion necesita para persistir clientes.
 */
public interface RepositorioClientesPort {

    Optional<Cliente> buscarPorLogin(String login);

    List<Cliente> buscarTodos();

    Cliente guardar(Cliente cliente);

    void borrarPorLogin(String login);

}
