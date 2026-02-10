package com.portfolio.ecommerce.servicio;

import com.portfolio.ecommerce.modelo.Producto;
import com.portfolio.ecommerce.repositorio.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImp implements ProductoServicio {

    @Autowired
    private ProductoRepo productoRepo;

    @Override
    public Producto save(Producto producto) {
        return productoRepo.save(producto);
    }

    @Override
    public Optional<Producto> get(Integer id) {
        return productoRepo.findById(id);
    }

    @Override
    public void update(Producto producto) {
        productoRepo.save(producto);
    }

    @Override
    public void delete(Integer id) {
        productoRepo.deleteById(id);
    }

    @Override
    public List<Producto> findAll() {
        return productoRepo.findAll();
    }
}
