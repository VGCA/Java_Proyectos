package com.portfolio.ecommerce.application.port.in;

import com.portfolio.ecommerce.domain.model.ArchivoImagen;
import com.portfolio.ecommerce.domain.model.Producto;

import java.util.List;

public interface ProductoUseCase {

    record DatosProducto(String nombre, String descripcion, double precio, int cantidad) {
    }

    List<Producto> listar();

    Producto obtener(Integer id);

    List<Producto> buscarPorNombre(String texto);

    Producto crear(DatosProducto datos, Integer idUsuarioCreador, ArchivoImagen imagen);

    Producto actualizar(Integer id, DatosProducto datos, ArchivoImagen imagen);

    void eliminar(Integer id);
}
