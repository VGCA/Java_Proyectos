package com.bosonit.tienda_back.servicios;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bosonit.tienda_back.dao.CategoriaDao;
import com.bosonit.tienda_back.modelo.Categoria;


@Service
@AllArgsConstructor
public class CategoriasServicio {

    private CategoriaDao categoriaRepo;

    public List<Categoria> buscarTodos() {
        return categoriaRepo.findAll();
    }
}
