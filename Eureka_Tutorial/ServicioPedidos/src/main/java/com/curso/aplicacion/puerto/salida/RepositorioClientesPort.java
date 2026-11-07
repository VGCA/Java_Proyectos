package com.curso.aplicacion.puerto.salida;

import java.util.Optional;

import com.curso.dominio.modelo.Cliente;

/** Copia local de los clientes que se necesitan para los pedidos. */
public interface RepositorioClientesPort {

    Optional<Cliente> buscarPorLogin(String login);

    Cliente guardar(Cliente cliente);

}
