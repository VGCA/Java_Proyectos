package com.bosonit.inventario.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bosonit.inventario.producto.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    
}
