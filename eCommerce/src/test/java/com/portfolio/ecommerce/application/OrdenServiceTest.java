package com.portfolio.ecommerce.application;

import com.portfolio.ecommerce.application.service.OrdenService;
import com.portfolio.ecommerce.domain.exception.RecursoNoEncontradoException;
import com.portfolio.ecommerce.domain.model.Carrito;
import com.portfolio.ecommerce.domain.model.Orden;
import com.portfolio.ecommerce.domain.model.Producto;
import com.portfolio.ecommerce.domain.model.Usuario;
import com.portfolio.ecommerce.support.Fakes.OrdenRepoEnMemoria;
import com.portfolio.ecommerce.support.Fakes.UsuarioRepoEnMemoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrdenServiceTest {

    private OrdenService service;
    private Integer idCliente;
    private Integer idOtro;

    @BeforeEach
    void preparar() {
        UsuarioRepoEnMemoria usuarios = new UsuarioRepoEnMemoria();
        idCliente = usuarios.guardar(new Usuario()).getId();
        idOtro = usuarios.guardar(new Usuario()).getId();
        Clock reloj = Clock.fixed(Instant.parse("2026-11-06T10:00:00Z"), ZoneOffset.UTC);
        service = new OrdenService(new OrdenRepoEnMemoria(), usuarios, reloj);
    }

    private Carrito carritoConUnProducto() {
        Producto p = new Producto();
        p.setId(1);
        p.setNombre("Monitor");
        p.setPrecio(100);
        Carrito c = new Carrito();
        c.agregar(p, 2);
        return c;
    }

    @Test
    void realizarPedidoCreaOrdenConTotalNumeroYFecha() {
        Orden o = service.realizarPedido(idCliente, carritoConUnProducto());
        assertEquals(200.0, o.getTotal(), 0.0001);
        assertEquals("0000000001", o.getNumero());
        assertEquals(1, o.getDetalle().size());
        assertEquals(Instant.parse("2026-11-06T10:00:00Z"), o.getFechaCreacion().toInstant());
    }

    @Test
    void losNumerosDeOrdenSonCorrelativosYSuperanLos999() {
        Orden o = null;
        for (int i = 0; i < 1001; i++) {
            o = service.realizarPedido(idCliente, carritoConUnProducto());
        }
        // El código original devolvía "" a partir de la orden 1000
        assertEquals("0000001001", o.getNumero());
    }

    @Test
    void noSeAceptaUnCarritoVacio() {
        assertThrows(IllegalStateException.class, () -> service.realizarPedido(idCliente, new Carrito()));
    }

    @Test
    void unClienteSoloPuedeVerSusPropiasOrdenes() {
        Orden o = service.realizarPedido(idCliente, carritoConUnProducto());
        assertEquals(o.getId(), service.obtenerDeUsuario(o.getId(), idCliente).getId());
        assertThrows(RecursoNoEncontradoException.class, () -> service.obtenerDeUsuario(o.getId(), idOtro));
    }
}
