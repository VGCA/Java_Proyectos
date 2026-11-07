package com.curso.aplicacion.servicio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.curso.DobleDePruebas.ClientesRemotosFake;
import com.curso.DobleDePruebas.RepositorioClientesFake;
import com.curso.DobleDePruebas.RepositorioPedidosFake;
import com.curso.dominio.excepcion.RecursoNoEncontradoException;
import com.curso.dominio.modelo.Cliente;
import com.curso.dominio.modelo.Pedido;

class GestorPedidosTest {

    private RepositorioClientesFake clientesLocales;
    private ClientesRemotosFake clientesRemotos;
    private GestorPedidos gestor;

    @BeforeEach
    void preparar() {
        clientesLocales = new RepositorioClientesFake();
        clientesRemotos = new ClientesRemotosFake();
        gestor = new GestorPedidos(clientesLocales, new RepositorioPedidosFake(), clientesRemotos);
    }

    private static Pedido pedidoDe(String login) {
        Cliente soloLogin = new Cliente(login, null, null, null, null);
        return new Pedido(null, "PED-0", "Hoy", "Pendiente", soloLogin, null);
    }

    @Test
    void conClienteLocalNoLlamaAlMicroservicio() {
        clientesLocales.guardar(new Cliente("a", "Ana", "Calle 1", "Calle 2", "600"));

        Pedido guardado = gestor.altaPedido(pedidoDe("a"));

        assertThat(clientesRemotos.llamadas).isZero();
        assertThat(guardado.id()).isEqualTo(1);
        assertThat(guardado.cliente().nombre()).isEqualTo("Ana");
    }

    @Test
    void sinClienteLocalLoTraeDelMicroservicioYLoGuarda() {
        clientesRemotos.datos.put("b", new Cliente("b", "Beto", "Calle 3", "Calle 4", "700"));

        Pedido guardado = gestor.altaPedido(pedidoDe("b"));

        assertThat(clientesRemotos.llamadas).isEqualTo(1);
        assertThat(clientesLocales.datos).containsKey("b");
        assertThat(guardado.cliente().nombre()).isEqualTo("Beto");
    }

    @Test
    void clienteInexistenteEnAmbosSitiosLanzaExcepcion() {
        assertThatThrownBy(() -> gestor.altaPedido(pedidoDe("nadie")))
                .isInstanceOf(RecursoNoEncontradoException.class)
                .hasMessage("Cliente no encontrado");
    }

    @Test
    void buscarPedidoInexistenteLanzaExcepcion() {
        assertThatThrownBy(() -> gestor.buscarPedido(99))
                .isInstanceOf(RecursoNoEncontradoException.class)
                .hasMessage("Pedido no encontrado");
    }

    @Test
    void listarDevuelveLosPedidosGuardados() {
        clientesLocales.guardar(new Cliente("a", "Ana", null, null, null));
        gestor.altaPedido(pedidoDe("a"));
        gestor.altaPedido(pedidoDe("a"));

        assertThat(gestor.listarPedidos()).hasSize(2);
    }

}
