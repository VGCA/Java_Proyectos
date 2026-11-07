package com.curso.aplicacion.servicio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.curso.RepositorioClientesFake;
import com.curso.dominio.excepcion.RecursoNoEncontradoException;
import com.curso.dominio.modelo.Cliente;

class GestorClientesTest {

    private GestorClientes gestor;

    @BeforeEach
    void preparar() {
        gestor = new GestorClientes(new RepositorioClientesFake());
    }

    private static Cliente cliente(String login) {
        return new Cliente(login, null, null, "Monty", null, "Calle 1", null, "600000000");
    }

    @Test
    void altaYBusqueda() {
        gestor.altaCliente(cliente("monty@hotmail.com"));

        assertThat(gestor.buscarCliente("monty@hotmail.com").nombre()).isEqualTo("Monty");
        assertThat(gestor.listarClientes()).hasSize(1);
    }

    @Test
    void buscarInexistenteLanzaExcepcion() {
        assertThatThrownBy(() -> gestor.buscarCliente("nadie"))
                .isInstanceOf(RecursoNoEncontradoException.class)
                .hasMessage("Cliente no encontrado");
    }

    @Test
    void modificarSustituyeLosDatosSinDuplicar() {
        gestor.altaCliente(cliente("a"));
        Cliente cambiado = new Cliente("a", null, null, "Otro nombre", null, "Calle 9", null, "611111111");

        gestor.modificarCliente(cambiado);

        assertThat(gestor.listarClientes()).hasSize(1);
        assertThat(gestor.buscarCliente("a").nombre()).isEqualTo("Otro nombre");
    }

    @Test
    void bajaEliminaYEsIdempotente() {
        gestor.altaCliente(cliente("a"));

        gestor.bajaCliente("a");
        gestor.bajaCliente("a");

        assertThat(gestor.listarClientes()).isEmpty();
    }

}
