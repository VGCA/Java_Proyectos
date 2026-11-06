package com.portfolio.ecommerce.infrastructure.adapter.in.web;

import com.portfolio.ecommerce.application.port.in.OrdenUseCase;
import com.portfolio.ecommerce.application.port.in.UsuarioUseCase;
import com.portfolio.ecommerce.infrastructure.adapter.in.security.UsuarioPrincipal;
import com.portfolio.ecommerce.infrastructure.adapter.in.web.form.UsuarioForm;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioUseCase usuarios;
    private final OrdenUseCase ordenes;

    public UsuarioController(UsuarioUseCase usuarios, OrdenUseCase ordenes) {
        this.usuarios = usuarios;
        this.ordenes = ordenes;
    }

    @GetMapping("/registro")
    public String create() {
        return "usuario/registro";
    }

    @PostMapping("/save")
    public String save(UsuarioForm form) {
        usuarios.registrar(form.aDatos());
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "usuario/login";
    }

    @GetMapping("/compras")
    public String obtenerCompras(@AuthenticationPrincipal UsuarioPrincipal principal, Model model) {
        model.addAttribute("ordenes", ordenes.listarDeUsuario(principal.getId()));
        return "usuario/compras";
    }

    @GetMapping("/detalle/{id}")
    public String detalleCompra(@PathVariable Integer id,
                                @AuthenticationPrincipal UsuarioPrincipal principal, Model model) {
        model.addAttribute("detalles", ordenes.obtenerDeUsuario(id, principal.getId()).getDetalle());
        return "usuario/detallecompra";
    }
}
