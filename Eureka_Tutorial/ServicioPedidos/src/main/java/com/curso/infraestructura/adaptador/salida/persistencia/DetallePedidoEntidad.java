package com.curso.infraestructura.adaptador.salida.persistencia;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "DETALLES_PEDIDO")
public class DetallePedidoEntidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "fk_id_pedido", referencedColumnName = "id")
    private PedidoEntidad pedido;
    @ManyToOne
    @JoinColumn(name = "fk_id_producto", referencedColumnName = "id")
    private ProductoEntidad producto;

    private Double precio;
    private Integer cantidad;

    public DetallePedidoEntidad() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PedidoEntidad getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntidad pedido) {
        this.pedido = pedido;
    }

    public ProductoEntidad getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntidad producto) {
        this.producto = producto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

}
