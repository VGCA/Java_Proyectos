package com.bosonit.inventario.controlador;

import java.util.List;

import com.bosonit.inventario.dtos.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bosonit.inventario.repositorio.CategoriaRepositorio;
import com.bosonit.inventario.categoria.Categoria;

@Controller
public class CategoriaController {

    private final CategoriaRepositorio categoriaRepo;

    @Autowired
    public CategoriaController(CategoriaRepositorio categoriaRepo) {
        this.categoriaRepo = categoriaRepo;
    }

    @GetMapping("/categorias")
    public String listarCategorias(Model modelo) {
        List<Categoria> listaCategorias = categoriaRepo.findAll();
        modelo.addAttribute("listaCategorias", listaCategorias);
        return "categorias";
    }

    @GetMapping("/categorias/nuevo")
    public String aniadirNuevaCategoria(Model modelo) {
        modelo.addAttribute("categoria", new Categoria());
        return "categoria_formulario";
    }

    @PostMapping("/categorias/guardar")
    public String guardarCategoria(@ModelAttribute CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripcion(categoriaDTO.getDescripcion());

        categoriaRepo.save(categoria);
        return "redirect:/categorias";
    }

}
