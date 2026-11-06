package com.portfolio.ecommerce.application.service;

import com.portfolio.ecommerce.application.port.in.ProductoUseCase;
import com.portfolio.ecommerce.application.port.out.ImagenStoragePort;
import com.portfolio.ecommerce.application.port.out.ProductoRepositoryPort;
import com.portfolio.ecommerce.application.port.out.UsuarioRepositoryPort;
import com.portfolio.ecommerce.domain.exception.RecursoNoEncontradoException;
import com.portfolio.ecommerce.domain.model.ArchivoImagen;
import com.portfolio.ecommerce.domain.model.Producto;
import com.portfolio.ecommerce.domain.model.Usuario;

import java.util.List;

public class ProductoService implements ProductoUseCase {

    private final ProductoRepositoryPort productos;
    private final UsuarioRepositoryPort usuarios;
    private final ImagenStoragePort imagenes;

    public ProductoService(ProductoRepositoryPort productos, UsuarioRepositoryPort usuarios,
                           ImagenStoragePort imagenes) {
        this.productos = productos;
        this.usuarios = usuarios;
        this.imagenes = imagenes;
    }

    @Override
    public List<Producto> listar() {
        return productos.buscarTodos();
    }

    @Override
    public Producto obtener(Integer id) {
        return productos.buscarPorId(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto", id));
    }

    @Override
    public List<Producto> buscarPorNombre(String texto) {
        return productos.buscarTodos().stream()
                .filter(p -> p.getNombre().contains(texto))
                .toList();
    }

    @Override
    public Producto crear(DatosProducto datos, Integer idUsuarioCreador, ArchivoImagen imagen) {
        Usuario creador = usuarios.buscarPorId(idUsuarioCreador)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario", idUsuarioCreador));

        Producto producto = new Producto();
        aplicar(datos, producto);
        producto.setUsuario(creador);
        producto.setImagen(imagen.esVacio() ? Producto.IMAGEN_POR_DEFECTO : imagenes.guardar(imagen));
        return productos.guardar(producto);
    }

    @Override
    public Producto actualizar(Integer id, DatosProducto datos, ArchivoImagen imagen) {
        Producto producto = obtener(id);
        aplicar(datos, producto);
        if (!imagen.esVacio()) {
            borrarImagenSiNoEsPorDefecto(producto);
            producto.setImagen(imagenes.guardar(imagen));
        }
        return productos.guardar(producto);
    }

    @Override
    public void eliminar(Integer id) {
        Producto producto = obtener(id);
        borrarImagenSiNoEsPorDefecto(producto);
        productos.eliminarPorId(id);
    }

    private void aplicar(DatosProducto datos, Producto destino) {
        destino.setNombre(datos.nombre());
        destino.setDescripcion(datos.descripcion());
        destino.setPrecio(datos.precio());
        destino.setCantidad(datos.cantidad());
    }

    private void borrarImagenSiNoEsPorDefecto(Producto producto) {
        if (!producto.tieneImagenPorDefecto()) {
            imagenes.eliminar(producto.getImagen());
        }
    }
}
