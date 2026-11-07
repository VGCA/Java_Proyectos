package com.curso.infraestructura.adaptador.salida.persistencia;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.curso.aplicacion.puerto.salida.RepositorioClientesPort;
import com.curso.dominio.modelo.Cliente;

@Component
public class ClientePersistenciaAdapter implements RepositorioClientesPort {

    private final ClienteJpaRepository jpa;

    public ClientePersistenciaAdapter(ClienteJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Optional<Cliente> buscarPorLogin(String login) {
        return jpa.findById(login).map(ClienteEntidadMapper::aDominio);
    }

    @Override
    public List<Cliente> buscarTodos() {
        return jpa.findAll().stream().map(ClienteEntidadMapper::aDominio).toList();
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        return ClienteEntidadMapper.aDominio(jpa.save(ClienteEntidadMapper.aEntidad(cliente)));
    }

    @Override
    public void borrarPorLogin(String login) {
        jpa.deleteById(login);
    }

}
