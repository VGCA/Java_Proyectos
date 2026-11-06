package com.portfolio.ecommerce.application.port.out;

import com.portfolio.ecommerce.domain.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoRepositoryPort {

    Producto guardar(Producto producto);

    Optional<Producto> buscarPorId(Integer id);

    List<Producto> buscarTodos();

    void eliminarPorId(Integer id);
}
