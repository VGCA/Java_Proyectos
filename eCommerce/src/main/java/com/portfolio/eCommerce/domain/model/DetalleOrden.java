package com.portfolio.ecommerce.domain.model;

/** Línea de una orden (o del carrito): un producto, una cantidad y su importe. */
public class DetalleOrden {

    private Integer id;
    private String nombre;
    private double cantidad;
    private double precio;
    private double total;
    private Producto producto;

    /** Crea una línea "congelando" nombre y precio del producto en el momento de la compra. */
    public static DetalleOrden desdeProducto(Producto producto, double cantidad) {
        DetalleOrden d = new DetalleOrden();
        d.producto = producto;
        d.nombre = producto.getNombre();
        d.precio = producto.getPrecio();
        d.cantidad = cantidad;
        d.total = producto.getPrecio() * cantidad;
        return d;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
