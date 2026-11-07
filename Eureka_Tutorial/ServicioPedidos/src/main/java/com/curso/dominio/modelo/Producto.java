package com.curso.dominio.modelo;

public record Producto(Integer id,
                       String nombre,
                       String fabricante,
                       Double precio,
                       Integer existencias) {
}
