package com.bosonit.inventario.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bosonit.inventario.marca.Marca;

public interface MarcaRepositorio extends JpaRepository<Marca,Integer>{
    
}
