package com.bosonit.inventario.controlador;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bosonit.inventario.categoria.Categoria;
import com.bosonit.inventario.producto.Producto;
import com.bosonit.inventario.repositorio.CategoriaRepositorio;
import com.bosonit.inventario.repositorio.ProductoRepository;

@Controller
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepo;

    @Autowired
    private CategoriaRepositorio categoriaRepo;

    @GetMapping("productos/nuevo")
    public String mostrarFormularioNuevoProducto(Model modelo) {
        List<Categoria> listaCategorias = categoriaRepo.findAll();

        modelo.addAttribute("producto", new Producto());
        modelo.addAttribute("listaCategorias", listaCategorias);

        return "producto_formulario";
    }

    @PostMapping("productos/guardar")
    public String guardarProducto(Producto producto, HttpServletRequest request) {

        String[] detallesNombres = request.getParameterValues("detallesNombres");
        String[] detallesValores = request.getParameterValues("detallesValores");
        String[] detallesIDs = request.getParameterValues("detallesIDs");

        for (int i = 0; i < detallesNombres.length; i++) {
            if (detallesIDs != null && detallesIDs.length > 0) {
                producto.setDetalle(Integer.valueOf(detallesIDs[i]), detallesNombres[i], detallesValores[i]);
            }else{
               producto.aniadirDetalles(detallesNombres[i], detallesValores[i]); 
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
        Producto producto = productoRepo.findById(id).get();
        modelo.addAttribute("producto", producto);

        List<Categoria> listaCategorias = categoriaRepo.findAll();
        modelo.addAttribute("listaCategorias", listaCategorias);

        return "producto_formulario";
    }

    @GetMapping("productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") int id, Model modelo) {
        productoRepo.deleteById(id);
        return "redirect:/productos";
    }
}
