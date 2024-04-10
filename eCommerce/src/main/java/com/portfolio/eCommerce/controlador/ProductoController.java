package com.portfolio.eCommerce.controlador;

import com.portfolio.eCommerce.modelo.Producto;
import com.portfolio.eCommerce.modelo.Usuario;
import com.portfolio.eCommerce.servicio.ProductoServicio;
import com.portfolio.eCommerce.servicio.UploadFileService;
import com.portfolio.eCommerce.servicio.UsuarioServicio;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UploadFileService subirArchivo;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public String show(Model model) {
        model.addAttribute("productos", productoServicio.findAll());
        return "productos/show";
    }

    @GetMapping("/create")
    public String create() {
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {

        LOGGER.info("Este es el objeto producto " + producto);
        Usuario user = usuarioServicio.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        producto.setUsuario(user);

        if (producto.getId() == null) { //Cuando se crea un producto nuevo
            String nombreImagen = subirArchivo.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        productoServicio.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Producto producto = new Producto();
        Optional<Producto> optionalProducto = productoServicio.get(id);
        producto = optionalProducto.get();
        LOGGER.info("Producto buscado" + producto);
        model.addAttribute("producto", producto);
        return "productos/edit";
    }

    @PostMapping("/update")
    public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
        Producto productoNuevo = new Producto();
        productoNuevo = productoServicio.get(producto.getId()).get();
        if (file.isEmpty()) { // Editamos el producto pero no cambiamos la imagen

            producto.setImagen(productoNuevo.getImagen());
        } else { //Cuando se edita la imagen

            // Eliminar cuando no sea la imagen por defecto
            if (!productoNuevo.getImagen().equals("default.jpg")) {
                subirArchivo.deleteImage(productoNuevo.getImagen());
            }
            String nombreImagen = subirArchivo.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        producto.setUsuario(productoNuevo.getUsuario());
        productoServicio.update(producto);
        return "redirect:/productos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Producto p = new Producto();
        p = productoServicio.get(id).get();
        // Eliminar cuando no sea la imagen por defecto
        if (!p.getImagen().equals("default.jpg")) {
            subirArchivo.deleteImage(p.getImagen());
        }
        productoServicio.delete(id);
        return "redirect:/productos";
    }
}
