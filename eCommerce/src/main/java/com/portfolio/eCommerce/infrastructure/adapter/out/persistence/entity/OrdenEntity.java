package com.portfolio.ecommerce.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orden")
public class OrdenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String numero;
    private Date fechaCreacion;
    private Date fechaRecibida;
    private double total;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    /** cascade ALL: al guardar la orden se guardan sus líneas en la misma transacción. */
    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
    private List<DetalleOrdenEntity> detalle = new ArrayList<>();

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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaRecibida() {
        return fechaRecibida;
    }

    public void setFechaRecibida(Date fechaRecibida) {
        this.fechaRecibida = fechaRecibida;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public List<DetalleOrdenEntity> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleOrdenEntity> detalle) {
        this.detalle = detalle;
    }
}
