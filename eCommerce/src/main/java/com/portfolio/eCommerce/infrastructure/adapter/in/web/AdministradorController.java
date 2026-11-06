package com.portfolio.ecommerce.infrastructure.adapter.in.web;

import com.portfolio.ecommerce.application.port.in.OrdenUseCase;
import com.portfolio.ecommerce.application.port.in.ProductoUseCase;
import com.portfolio.ecommerce.application.port.in.UsuarioUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    private final ProductoUseCase productos;
    private final UsuarioUseCase usuarios;
    private final OrdenUseCase ordenes;

    public AdministradorController(ProductoUseCase productos, UsuarioUseCase usuarios, OrdenUseCase ordenes) {
        this.productos = productos;
        this.usuarios = usuarios;
        this.ordenes = ordenes;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("productos", productos.listar());
        return "administrador/home";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", usuarios.listar());
        return "administrador/usuarios";
    }

    @GetMapping("/ordenes")
    public String ordenes(Model model) {
        model.addAttribute("ordenes", ordenes.listar());
        return "administrador/ordenes";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Integer id, Model model) {
        model.addAttribute("detalles", ordenes.obtener(id).getDetalle());
        return "administrador/detalleorden";
    }
}
