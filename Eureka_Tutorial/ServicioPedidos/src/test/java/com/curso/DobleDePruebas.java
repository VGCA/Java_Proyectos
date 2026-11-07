package com.curso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.curso.aplicacion.puerto.salida.ClientesRemotosPort;
import com.curso.aplicacion.puerto.salida.RepositorioClientesPort;
import com.curso.aplicacion.puerto.salida.RepositorioPedidosPort;
import com.curso.dominio.modelo.Cliente;
import com.curso.dominio.modelo.Pedido;

/** Implementaciones en memoria de los puertos de salida, para probar el nucleo sin Spring. */
public final class DobleDePruebas {

    private DobleDePruebas() {
    }

    public static class RepositorioClientesFake implements RepositorioClientesPort {
        public final Map<String, Cliente> datos = new HashMap<>();

        @Override
        public Optional<Cliente> buscarPorLogin(String login) {
            return Optional.ofNullable(datos.get(login));
        }

        @Override
        public Cliente guardar(Cliente cliente) {
            datos.put(cliente.login(), cliente);
            return cliente;
        }
    }

    public static class RepositorioPedidosFake implements RepositorioPedidosPort {
        private final Map<Integer, Pedido> datos = new LinkedHashMap<>();
        private int siguienteId = 1;

        @Override
        public Pedido guardar(Pedido pedido) {
            Pedido p = (pedido.id() == null) ? pedido.conId(siguienteId++) : pedido;
            datos.put(p.id(), p);
            return p;
        }

        @Override
        public Optional<Pedido> buscarPorId(Integer id) {
            return Optional.ofNullable(datos.get(id));
        }

        @Override
        public List<Pedido> buscarTodos() {
            return new ArrayList<>(datos.values());
        }
    }

    public static class ClientesRemotosFake implements ClientesRemotosPort {
        public final Map<String, Cliente> datos = new HashMap<>();
        public int llamadas = 0;

        @Override
        public Optional<Cliente> buscarPorLogin(String login) {
            llamadas++;
            return Optional.ofNullable(datos.get(login));
        }
    }

}
