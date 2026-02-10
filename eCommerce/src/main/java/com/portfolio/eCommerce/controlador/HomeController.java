package com.portfolio.ecommerce.controlador;

import com.portfolio.ecommerce.modelo.DetalleOrden;
import com.portfolio.ecommerce.modelo.Orden;
import com.portfolio.ecommerce.modelo.Producto;
import com.portfolio.ecommerce.modelo.Usuario;
import com.portfolio.ecommerce.servicio.DetalleOrdenServicio;
import com.portfolio.ecommerce.servicio.OrdenServicio;
import com.portfolio.ecommerce.servicio.ProductoServicio;
import com.portfolio.ecommerce.servicio.UsuarioServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {

    private final ProductoServicio productoServicio;

    private final UsuarioServiceImp usuarioServiceImp;

    private final OrdenServicio ordenServicio;

    private final DetalleOrdenServicio detalleOrdenServicio;

    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

    Orden orden = new Orden();

    public HomeController(ProductoServicio productoServicio, UsuarioServiceImp usuarioServiceImp, OrdenServicio ordenServicio, DetalleOrdenServicio detalleOrdenServicio) {
        this.productoServicio = productoServicio;
        this.usuarioServiceImp = usuarioServiceImp;
        this.ordenServicio = ordenServicio;
        this.detalleOrdenServicio = detalleOrdenServicio;
    }

    @GetMapping
    public String home(Model model, HttpSession session) {

        model.addAttribute("productos", productoServicio.findAll());
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "usuario/home";
    }

    @GetMapping("productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model) {


        Producto producto;
        Optional<Producto> productoOptional = productoServicio.get(id);
        producto = productoOptional.get();
        model.addAttribute("producto", producto);

        return "usuario/productohome";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {

        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto;
        double sumaTotal = 0;

        Optional<Producto> optionalProducto = productoServicio.get(id);

        producto = optionalProducto.get();
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio() * cantidad);
        detalleOrden.setProducto(producto);

        // Validamos que el producto no se añada dos veces
        Integer idProducto = producto.getId();
        boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto);

        if (!ingresado) {
            detalles.add(detalleOrden);
        }

        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }

    @GetMapping("/delete/cart/{id}")
    public String deleteProduct(@PathVariable Integer id, Model model) {
        List<DetalleOrden> ordenesNuevas = new ArrayList<DetalleOrden>();
        for (DetalleOrden detalleOrden : detalles) {
            if (detalleOrden.getProducto().getId() != id) {
                ordenesNuevas.add(detalleOrden);
            }
        }
        detalles = ordenesNuevas;
        double sumaTotal = 0;
        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }

    @GetMapping("/getCart")
    public String getCart(Model model,HttpSession session) {

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);

        // Sesión
        model.addAttribute("sesion",session.getAttribute("idusuario"));

        return "/usuario/carrito";
    }

    @GetMapping("/order")
    public String order(Model model, HttpSession session) {

        Usuario usuario = usuarioServiceImp.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario", usuario);

        return "usuario/resumenorden";
    }

    @PostMapping("/search")
    public String searchProduct(@RequestParam String nombre, Model model) {

        List<Producto> productos = productoServicio.findAll().stream().filter(p -> p.getNombre().contains(nombre)).collect(Collectors.toList());
        model.addAttribute("productos", productos);
        return "usuario/home";
    }

    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession session) {

        Date fechaCreacion = new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(ordenServicio.generarNumeroOrden());

        Usuario usuario = usuarioServiceImp.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        orden.setUsuario(usuario);
        ordenServicio.save(orden);

        for (DetalleOrden detalleOrden : detalles) {
            detalleOrden.setOrden(orden);
            detalleOrdenServicio.save(detalleOrden);
        }

        // Limpiar lista y orden
        orden = new Orden();
        detalles.clear();

        return "redirect:/";
    }
}
