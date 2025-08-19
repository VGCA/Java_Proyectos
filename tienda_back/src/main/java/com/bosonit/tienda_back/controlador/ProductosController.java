package com.bosonit.tienda_back.controlador;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bosonit.tienda_back.modelo.Categoria;
import com.bosonit.tienda_back.modelo.Producto;
import com.bosonit.tienda_back.servicios.CategoriasServicio;
import com.bosonit.tienda_back.servicios.ProductosServicio;

@CrossOrigin(origins = { "*" })
@RestController
@AllArgsConstructor
@RequestMapping("/api/productos")
public class ProductosController {

    private final ProductosServicio productosServicio;
    private final CategoriasServicio categoriasServicio;

    @Autowired
    public ProductosController(ProductosServicio productosServicio, CategoriasServicio categoriasServicio) {
        this.productosServicio = productosServicio;
        this.categoriasServicio = categoriasServicio;
    }

    @GetMapping("/listar")
    public List<Producto> listarProductos() {
        return productosServicio.buscarTodos();
    }

    @GetMapping("/categorias")
    public List<Categoria> listarCategorias() {
        return categoriasServicio.buscarTodos();
    }

    @PostMapping("/listar")
    public Producto aniadirProducto(@RequestBody Producto producto) {
        return productosServicio.guardar(producto);
    }

    @GetMapping("/listar/{id}")
    public Producto buscarProducto(@PathVariable("id") int id) {
        return productosServicio.buscarID(id);
    }

    /**
     * FUNCIÓN UTILIZADA PARA ACTUALIZAR LOS PRODUCTOS
     * A TRAVÉS DEL FRONT
     */
    @GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable("id") int id) {
        return productosServicio.buscarID(id);
    }

    @DeleteMapping("/listar/{id}")
    public void borrarProducto(@PathVariable("id") int id) {
        productosServicio.deleteProducto(id);
    }

    @PutMapping("/listar/{id}")
    public Producto actualizarProducto(@PathVariable("id") int id, @RequestBody Producto producto) {
        Producto encontrado = productosServicio.buscarID(id);
        if (encontrado != null) {
            encontrado.setNombre(producto.getNombre());
            encontrado.setPrecio(producto.getPrecio());
            encontrado.setImagen(producto.getImagen());
            encontrado.setCategoria(producto.getCategoria());
            return encontrado;
        }
        return null;
    }
}
