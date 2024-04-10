package com.bosonit.inventario.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bosonit.inventario.categoria.Categoria;
import com.bosonit.inventario.marca.Marca;
import com.bosonit.inventario.repositorio.CategoriaRepositorio;
import com.bosonit.inventario.repositorio.MarcaRepositorio;

@Controller
public class MarcaController {

    @Autowired
    private MarcaRepositorio marcaRepo;

    @Autowired
    private CategoriaRepositorio categoriaRepo;

    @GetMapping("marcas/nueva")
    public String mostrarFormularioCrearMarca(Model modelo) {
        List<Categoria> listaCategorias = categoriaRepo.findAll();

        modelo.addAttribute("listaCategorias", listaCategorias);
        modelo.addAttribute("marca", new Marca());

        return "marca_formulario";
    }

    @PostMapping("marcas/guardar")
    public String guardarMarca(Marca marca) {
        marcaRepo.save(marca);
        return "redirect:/";
    }

    @GetMapping("marcas")
    public String listarMarcas(Model modelo) {
        List<Marca> listaMarcas = marcaRepo.findAll();
        modelo.addAttribute("listaMarcas", listaMarcas);
        return "marcas";
    }

    @GetMapping("marcas/editar/{id}")
    public String mostrarFormularioModificarMarca(@PathVariable("id") int id, Model modelo) {
        List<Categoria> listaCategorias = categoriaRepo.findAll();
        Marca marca = marcaRepo.findById(id).get();

        modelo.addAttribute("listaCategorias", listaCategorias);
        modelo.addAttribute("marca", marca);

        return "marca_formulario";
    }

}
