package com.curso;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.curso.aplicacion.puerto.salida.RepositorioClientesPort;
import com.curso.dominio.modelo.Cliente;

/** Implementacion en memoria del puerto de salida, para probar el nucleo sin Spring. */
public class RepositorioClientesFake implements RepositorioClientesPort {

    private final Map<String, Cliente> datos = new LinkedHashMap<>();

    @Override
    public Optional<Cliente> buscarPorLogin(String login) {
        return Optional.ofNullable(datos.get(login));
    }

    @Override
    public List<Cliente> buscarTodos() {
        return new ArrayList<>(datos.values());
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        datos.put(cliente.login(), cliente);
        return cliente;
    }

    @Override
    public void borrarPorLogin(String login) {
        datos.remove(login);
    }

}
