package com.portfolio.ecommerce.domain.model;

import java.util.ArrayList;
import java.util.List;

/** Orden de compra. Agrega sus líneas de detalle (aggregate root). */
public class Orden {

    private Integer id;
    private String numero;
    private java.util.Date fechaCreacion;
    private java.util.Date fechaRecibida;
    private double total;
    private Usuario usuario;
    private List<DetalleOrden> detalle = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public java.util.Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(java.util.Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public java.util.Date getFechaRecibida() {
        return fechaRecibida;
    }

    public void setFechaRecibida(java.util.Date fechaRecibida) {
        this.fechaRecibida = fechaRecibida;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetalleOrden> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleOrden> detalle) {
        this.detalle = detalle;
    }
}
