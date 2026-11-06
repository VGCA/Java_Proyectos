package com.portfolio.ecommerce.infrastructure.adapter.in.web;

import com.portfolio.ecommerce.application.port.in.OrdenUseCase;
import com.portfolio.ecommerce.application.port.in.ProductoUseCase;
import com.portfolio.ecommerce.application.port.in.UsuarioUseCase;
import com.portfolio.ecommerce.domain.model.Carrito;
import com.portfolio.ecommerce.domain.model.Orden;
import com.portfolio.ecommerce.domain.model.Producto;
import com.portfolio.ecommerce.infrastructure.adapter.in.security.UsuarioPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    private static final String VISTA_CARRITO = "usuario/carrito";

    private final ProductoUseCase productos;
    private final UsuarioUseCase usuarios;
    private final OrdenUseCase ordenes;
    private final CarritoSesion carritoSesion;

    public HomeController(ProductoUseCase productos, UsuarioUseCase usuarios,
                          OrdenUseCase ordenes, CarritoSesion carritoSesion) {
        this.productos = productos;
        this.usuarios = usuarios;
        this.ordenes = ordenes;
        this.carritoSesion = carritoSesion;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("productos", productos.listar());
        return "usuario/home";
    }

    @GetMapping("productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model) {
        model.addAttribute("producto", productos.obtener(id));
        return "usuario/productohome";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
        Producto producto = productos.obtener(id);
        carritoSesion.get().agregar(producto, cantidad);
        return mostrarCarrito(model);
    }

    @GetMapping("/delete/cart/{id}")
    public String deleteProduct(@PathVariable Integer id, Model model) {
        carritoSesion.get().quitar(id);
        return mostrarCarrito(model);
    }

    @GetMapping("/getCart")
    public String getCart(Model model) {
        return mostrarCarrito(model);
    }

    @GetMapping("/order")
    public String order(@AuthenticationPrincipal UsuarioPrincipal principal, Model model) {
        Carrito carrito = carritoSesion.get();
        model.addAttribute("cart", carrito.getLineas());
        model.addAttribute("orden", resumen(carrito));
        model.addAttribute("usuario", usuarios.obtener(principal.getId()));
        return "usuario/resumenorden";
    }

    @PostMapping("/search")
    public String searchProduct(@RequestParam String nombre, Model model) {
        model.addAttribute("productos", productos.buscarPorNombre(nombre));
        return "usuario/home";
    }

    @GetMapping("/saveOrder")
    public String saveOrder(@AuthenticationPrincipal UsuarioPrincipal principal) {
        Carrito carrito = carritoSesion.get();
        if (carrito.estaVacio()) {
            return "redirect:/getCart";
        }
        ordenes.realizarPedido(principal.getId(), carrito);
        carrito.vaciar();
        return "redirect:/";
    }

    private String mostrarCarrito(Model model) {
        Carrito carrito = carritoSesion.get();
        model.addAttribute("cart", carrito.getLineas());
        model.addAttribute("orden", resumen(carrito));
        return VISTA_CARRITO;
    }

    /** Las plantillas esperan un objeto "orden" con el total; se construye solo para la vista. */
    private Orden resumen(Carrito carrito) {
        Orden resumen = new Orden();
        resumen.setTotal(carrito.total());
        return resumen;
    }
}
