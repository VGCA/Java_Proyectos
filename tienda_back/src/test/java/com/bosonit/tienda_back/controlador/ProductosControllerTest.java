package com.bosonit.tienda_back.controlador;

import com.bosonit.tienda_back.modelo.Producto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductosControllerTest {

    @Mock
    private ProductosController productosController;

    @Test
    void listarProductos() {
        int sizeList = productosController.listarProductos().size();
        Assertions.assertEquals(0,sizeList);
    }

    @Test
    void listarCategorias() {
    }

    @Test
    void aniadirProducto() {
    }

    @Test
    void buscarProducto() {
    }

    @Test
    void obtenerProducto() {
    }

    @Test
    void borrarProducto() {
    }

    @Test
    void actualizarProducto() {
    }
}