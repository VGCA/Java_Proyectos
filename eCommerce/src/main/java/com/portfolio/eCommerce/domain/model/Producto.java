package com.portfolio.ecommerce.domain.model;

/** Producto a la venta. {@code usuario} es el administrador que lo dio de alta. */
public class Producto {

    public static final String IMAGEN_POR_DEFECTO = "default.jpg";

    private Integer id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private double precio;
    private int cantidad;
    private Usuario usuario;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean tieneImagenPorDefecto() {
        return imagen == null || IMAGEN_POR_DEFECTO.equals(imagen);
    }
}
