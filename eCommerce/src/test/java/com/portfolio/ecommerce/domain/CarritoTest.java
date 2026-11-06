package com.portfolio.ecommerce.domain;

import com.portfolio.ecommerce.domain.model.Carrito;
import com.portfolio.ecommerce.domain.model.Producto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CarritoTest {

    private Producto producto(int id, double precio) {
        Producto p = new Producto();
        p.setId(id);
        p.setNombre("P" + id);
        p.setPrecio(precio);
        return p;
    }

    @Test
    void calculaElTotalSumandoLasLineas() {
        Carrito c = new Carrito();
        c.agregar(producto(1, 10.0), 2);
        c.agregar(producto(2, 5.5), 1);
        assertEquals(25.5, c.total(), 0.0001);
    }

    @Test
    void noDuplicaUnProductoYaAnadido() {
        Carrito c = new Carrito();
        Producto p = producto(1, 10.0);
        c.agregar(p, 1);
        c.agregar(p, 5);
        assertEquals(1, c.getLineas().size());
        assertEquals(10.0, c.total(), 0.0001);
    }

    @Test
    void quitarComparaIdsPorValorNoPorReferencia() {
        // El original usaba "!=" entre Integer y fallaba con ids > 127
        Carrito c = new Carrito();
        c.agregar(producto(1000, 3.0), 1);
        c.quitar(Integer.valueOf(1000));
        assertTrue(c.estaVacio());
    }

    @Test
    void vaciarDejaElCarritoSinLineas() {
        Carrito c = new Carrito();
        c.agregar(producto(1, 1.0), 1);
        c.vaciar();
        assertFalse(c.contiene(1));
        assertTrue(c.estaVacio());
    }
}
