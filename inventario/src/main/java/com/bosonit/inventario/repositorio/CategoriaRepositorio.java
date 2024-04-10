package com.bosonit.inventario.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bosonit.inventario.categoria.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria,Integer>{
    
}
