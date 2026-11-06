package com.portfolio.ecommerce.infrastructure.adapter.out.persistence;

import com.portfolio.ecommerce.application.port.out.ProductoRepositoryPort;
import com.portfolio.ecommerce.domain.model.Producto;
import com.portfolio.ecommerce.infrastructure.adapter.out.persistence.entity.UsuarioEntity;
import com.portfolio.ecommerce.infrastructure.adapter.out.persistence.repository.ProductoJpaRepository;
import com.portfolio.ecommerce.infrastructure.adapter.out.persistence.repository.UsuarioJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional(readOnly = true)
public class ProductoPersistenceAdapter implements ProductoRepositoryPort {

    private final ProductoJpaRepository productos;
    private final UsuarioJpaRepository usuarios;

    public ProductoPersistenceAdapter(ProductoJpaRepository productos, UsuarioJpaRepository usuarios) {
        this.productos = productos;
        this.usuarios = usuarios;
    }

    @Override
    @Transactional
    public Producto guardar(Producto producto) {
        UsuarioEntity propietario = producto.getUsuario() == null
                ? null
                : usuarios.getReferenceById(producto.getUsuario().getId());
        return PersistenceMapper.aDominio(productos.save(PersistenceMapper.aEntidad(producto, propietario)));
    }

    @Override
    public Optional<Producto> buscarPorId(Integer id) {
        return productos.findById(id).map(PersistenceMapper::aDominio);
    }

    @Override
    public List<Producto> buscarTodos() {
        return productos.findAll().stream().map(PersistenceMapper::aDominio).toList();
    }

    @Override
    @Transactional
    public void eliminarPorId(Integer id) {
        productos.deleteById(id);
    }
}
