package com.curso.aplicacion.puerto.entrada;

import java.util.List;

import com.curso.dominio.modelo.Cliente;

/**
 * Puerto de entrada: casos de uso que ofrece la aplicacion.
 */
public interface GestionClientes {

    Cliente buscarCliente(String login);

    List<Cliente> listarClientes();

    Cliente altaCliente(Cliente cliente);

    Cliente modificarCliente(Cliente cliente);

    void bajaCliente(String login);

}
