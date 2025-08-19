package com.bosonit.inventario.dtos;

import java.util.List;

public class ProductoDTO {
    private String nombre;
    private String descripcion;
    private List<DetalleDTO> detalles;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<DetalleDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleDTO> detalles) {
        this.detalles = detalles;
    }
}

