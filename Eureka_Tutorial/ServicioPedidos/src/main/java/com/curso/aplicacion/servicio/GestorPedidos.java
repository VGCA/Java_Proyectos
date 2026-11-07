package com.curso.aplicacion.servicio;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.curso.aplicacion.puerto.entrada.GestionPedidos;
import com.curso.aplicacion.puerto.salida.ClientesRemotosPort;
import com.curso.aplicacion.puerto.salida.RepositorioClientesPort;
import com.curso.aplicacion.puerto.salida.RepositorioPedidosPort;
import com.curso.dominio.excepcion.RecursoNoEncontradoException;
import com.curso.dominio.modelo.Cliente;
import com.curso.dominio.modelo.Pedido;

/**
 * Unico punto del nucleo que usa una anotacion de Spring (@Transactional): el alta del pedido
 * guarda cliente y pedido y debe ser atomica, igual que en la version original.
 */
@Transactional(propagation = Propagation.REQUIRED)
public class GestorPedidos implements GestionPedidos {

    private static final Logger log = LoggerFactory.getLogger(GestorPedidos.class);

    private final RepositorioClientesPort repoClientes;
    private final RepositorioPedidosPort repoPedidos;
    private final ClientesRemotosPort clientesRemotos;

    public GestorPedidos(RepositorioClientesPort repoClientes,
                         RepositorioPedidosPort repoPedidos,
                         ClientesRemotosPort clientesRemotos) {
        this.repoClientes = repoClientes;
        this.repoPedidos = repoPedidos;
        this.clientesRemotos = clientesRemotos;
    }

    @Override
    public Pedido altaPedido(Pedido pedido) {
        String login = pedido.cliente().login();

        Cliente cliente = repoClientes.buscarPorLogin(login)
                .orElseGet(() -> {
                    log.info("Invocando al microservicio de clientes");
                    return clientesRemotos.buscarPorLogin(login)
                            .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado"));
                });

        repoClientes.guardar(cliente);
        return repoPedidos.guardar(pedido.conCliente(cliente));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> listarPedidos() {
        return repoPedidos.buscarTodos();
    }

    @Override
    @Transactional(readOnly = true)
    public Pedido buscarPedido(Integer id) {
        return repoPedidos.buscarPorId(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Pedido no encontrado"));
    }

    @Override
    public void modificarPedido(Pedido pedido) {
        // TODO: no implementado en la logica de negocio (igual que en el original)
    }

    @Override
    public void borrarPedido(Integer idPedido) {
        // TODO: no implementado en la logica de negocio (igual que en el original)
    }

}
