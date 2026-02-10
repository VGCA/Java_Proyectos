package com.portfolio.ecommerce.controlador;

import com.portfolio.ecommerce.modelo.Orden;
import com.portfolio.ecommerce.modelo.Producto;
import com.portfolio.ecommerce.servicio.OrdenServicio;
import com.portfolio.ecommerce.servicio.ProductoServicio;
import com.portfolio.ecommerce.servicio.UsuarioServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    private final ProductoServicio productoServicio;

    private final UsuarioServicio usuarioServicio;

    private final OrdenServicio ordenServicio;

    public AdministradorController(ProductoServicio productoServicio, UsuarioServicio usuarioServicio, OrdenServicio ordenServicio) {
        this.productoServicio = productoServicio;
        this.usuarioServicio = usuarioServicio;
        this.ordenServicio = ordenServicio;
    }

    @GetMapping
    public String home(Model model) {
        List<Producto> productos = productoServicio.findAll();
        model.addAttribute("productos", productos);
        return "administrador/home";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", usuarioServicio.findAll());
        return "administrador/usuarios";
    }

    @GetMapping("/ordenes")
    public String ordenes(Model model) {
        model.addAttribute("ordenes", ordenServicio.findAll());
        return "administrador/ordenes";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(Model model, @PathVariable Integer id) {
        Orden orden = ordenServicio.findById(id).get();
        model.addAttribute("detalles", orden.getDetalle());
        return "administrador/detalleorden";
    }
}
