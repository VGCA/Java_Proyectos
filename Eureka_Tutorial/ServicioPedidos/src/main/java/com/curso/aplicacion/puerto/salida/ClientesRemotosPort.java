package com.curso.aplicacion.puerto.salida;

import java.util.Optional;

import com.curso.dominio.modelo.Cliente;

/** Acceso al microservicio de clientes. Devuelve vacio si el cliente no existe alli. */
public interface ClientesRemotosPort {

    Optional<Cliente> buscarPorLogin(String login);

}
