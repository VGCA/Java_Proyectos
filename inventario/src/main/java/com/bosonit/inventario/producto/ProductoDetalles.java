package com.bosonit.inventario.producto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "producto_detalles")
public class ProductoDetalles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 45, nullable = false)
    private String nombre;

    @Column(length = 45, nullable = false)
    private String valor;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    public ProductoDetalles(String nombre, String valor, Producto producto) {
        super();
        this.nombre = nombre;
        this.valor = valor;
        this.producto = producto;
    }

    public ProductoDetalles(int id,String nombre, String valor, Producto producto) {
        super();
        this.id=id;
        this.nombre = nombre;
        this.valor = valor;
        this.producto = producto;
    }

    @Override
    public String toString(){
        return nombre+" - "+valor;
    }

   

}
