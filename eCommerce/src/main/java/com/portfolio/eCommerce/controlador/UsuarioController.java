package com.portfolio.ecommerce.controlador;

import com.portfolio.ecommerce.modelo.Orden;
import com.portfolio.ecommerce.modelo.Usuario;
import com.portfolio.ecommerce.servicio.OrdenServicio;
import com.portfolio.ecommerce.servicio.UsuarioServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioServicio usuarioServicio;

    private final OrdenServicio ordenServicio;

    BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();

    public UsuarioController(UsuarioServicio usuarioServicio, OrdenServicio ordenServicio) {
        this.usuarioServicio = usuarioServicio;
        this.ordenServicio = ordenServicio;
    }

    @GetMapping("/registro")
    public String create() {
        return "usuario/registro";
    }

    @PostMapping("/save")
    public String save(Usuario usuario) {
        usuario.setTipo("USER");
        usuario.setPassword(passEncode.encode(usuario.getPassword()));
        usuarioServicio.save(usuario);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "usuario/login";
    }

    @GetMapping("/acceder")
    public String acceder(Usuario usuario, HttpSession session) {
        Optional<Usuario> user = usuarioServicio.findById(Integer.parseInt(session.getAttribute("idusuario").toString()));

        if (user.isPresent()) {
            session.setAttribute("idusuario", user.get().getId());
            if (user.get().getTipo().equals("ADMIN")) {
                return "redirect:/administrador";
            } else {
                return "redirect:/";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/compras")
    public String obtenerCompras(Model model, HttpSession session) {
        model.addAttribute("sesion", session.getAttribute("idusuario"));

        Usuario usuario = usuarioServicio.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        List<Orden> ordenes = ordenServicio.findByUsuario(usuario);
        model.addAttribute("ordenes", ordenes);

        return "usuario/compras";
    }

    @GetMapping("/detalle/{id}")
    public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model) {
        Optional<Orden> orden = ordenServicio.findById(id);
        model.addAttribute("detalles", orden.get().getDetalle());

        //Sesión
        model.addAttribute("sesion", session.getAttribute("idusuario"));

        return "usuario/detallecompra";
    }

    @GetMapping("/cerrar")
    public String cerrarSesion(HttpSession session) {
        session.removeAttribute("idusuario");
        return "redirect:/";
    }
}
