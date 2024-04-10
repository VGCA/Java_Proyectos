package com.portfolio.eCommerce.servicio;

import com.portfolio.eCommerce.modelo.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoServicio {
    public Producto save(Producto producto);
    public Optional<Producto> get(Integer id);
    public void update(Producto producto);
    public void delete(Integer id);
    public List<Producto> findAll();
}
