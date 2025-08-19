package com.bosonit.inventario.controlador;

import com.bosonit.inventario.categoria.Categoria;
import com.bosonit.inventario.dtos.DetalleDTO;
import com.bosonit.inventario.dtos.ProductoDTO;
import com.bosonit.inventario.producto.Producto;
import com.bosonit.inventario.repositorio.CategoriaRepositorio;
import com.bosonit.inventario.repositorio.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductoController {

    private final ProductoRepository productoRepo;
    private final CategoriaRepositorio categoriaRepo;

    @Autowired
    public ProductoController(ProductoRepository productoRepo, CategoriaRepositorio categoriaRepo) {
        this.productoRepo = productoRepo;
        this.categoriaRepo = categoriaRepo;
    }

    @GetMapping("productos/nuevo")
    public String mostrarFormularioNuevoProducto(Model modelo) {
        List<Categoria> listaCategorias = categoriaRepo.findAll();

        modelo.addAttribute("producto", new Producto());
        modelo.addAttribute("listaCategorias", listaCategorias);

        return "producto_formulario";
    }

    @PostMapping("productos/guardar")
    public String guardarProducto(@ModelAttribute ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());

        for (DetalleDTO detalleDTO : productoDTO.getDetalles()) {
            if (detalleDTO.getId() != null) {
                producto.setDetalle(detalleDTO.getId(), detalleDTO.getNombre(), detalleDTO.getValor());
            } else {
                producto.aniadirDetalles(detalleDTO.getNombre(), detalleDTO.getValor());
            }
        }

        productoRepo.save(producto);
        return "redirect:/";
    }


    @GetMapping("productos")
    public String listarProductos(Model modelo) {
        List<Producto> listaProductos = productoRepo.findAll();
        modelo.addAttribute("listaProductos", listaProductos);
        return "productos";
    }

    @GetMapping("productos/editar/{id}")
    public String mostrarFormularioModificarProducto(@PathVariable("id") int id, Model modelo) {
        Optional<Producto> productoOpt = productoRepo.findById(id);

        if (productoOpt.isEmpty()) {

            return "redirect:/productos?error=notfound";
        }

        Producto producto = productoOpt.get();
        modelo.addAttribute("producto", producto);

        List<Categoria> categorias = categoriaRepo.findAll();
        modelo.addAttribute("listaCategorias", categorias);

        return "producto_formulario";
    }


    @GetMapping("productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") int id, Model modelo) {
        productoRepo.deleteById(id);
        return "redirect:/productos";
    }
}
