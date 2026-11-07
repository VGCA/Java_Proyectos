package com.curso.aplicacion.servicio;

import java.util.List;

import com.curso.aplicacion.puerto.entrada.GestionClientes;
import com.curso.aplicacion.puerto.salida.RepositorioClientesPort;
import com.curso.dominio.excepcion.RecursoNoEncontradoException;
import com.curso.dominio.modelo.Cliente;

public class GestorClientes implements GestionClientes {

    private final RepositorioClientesPort repositorio;

    public GestorClientes(RepositorioClientesPort repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Cliente buscarCliente(String login) {
        return repositorio.buscarPorLogin(login)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado"));
    }

    @Override
    public List<Cliente> listarClientes() {
        return repositorio.buscarTodos();
    }

    @Override
    public Cliente altaCliente(Cliente cliente) {
        // Logica de negocio para el alta...
        return repositorio.guardar(cliente);
    }

    @Override
    public Cliente modificarCliente(Cliente cliente) {
        // Logica de negocio para modificar...
        return repositorio.guardar(cliente);
    }

    @Override
    public void bajaCliente(String login) {
        // Logica de negocio para la baja...
        repositorio.borrarPorLogin(login);
    }

}
