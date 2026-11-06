package com.portfolio.ecommerce.infrastructure.adapter.in.web;

import com.portfolio.ecommerce.application.port.in.ProductoUseCase;
import com.portfolio.ecommerce.domain.model.ArchivoImagen;
import com.portfolio.ecommerce.infrastructure.adapter.in.security.UsuarioPrincipal;
import com.portfolio.ecommerce.infrastructure.adapter.in.web.form.ProductoForm;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private static final String REDIRECT_LISTADO = "redirect:/productos";

    private final ProductoUseCase productos;

    public ProductoController(ProductoUseCase productos) {
        this.productos = productos;
    }

    @GetMapping
    public String show(Model model) {
        model.addAttribute("productos", productos.listar());
        return "productos/show";
    }

    @GetMapping("/create")
    public String create() {
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(ProductoForm form, @RequestParam("img") MultipartFile img,
                       @AuthenticationPrincipal UsuarioPrincipal principal) throws IOException {
        productos.crear(form.aDatos(), principal.getId(), aArchivo(img));
        return REDIRECT_LISTADO;
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("producto", productos.obtener(id));
        return "productos/edit";
    }

    @PostMapping("/update")
    public String update(ProductoForm form, @RequestParam("img") MultipartFile img) throws IOException {
        productos.actualizar(form.id(), form.aDatos(), aArchivo(img));
        return REDIRECT_LISTADO;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productos.eliminar(id);
        return REDIRECT_LISTADO;
    }

    private ArchivoImagen aArchivo(MultipartFile file) throws IOException {
        return file.isEmpty()
                ? ArchivoImagen.ninguno()
                : new ArchivoImagen(file.getOriginalFilename(), file.getBytes());
    }
}
