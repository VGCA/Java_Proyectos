package com.portfolio.ecommerce.infrastructure.adapter.out.persistence;

import com.portfolio.ecommerce.application.port.out.OrdenRepositoryPort;
import com.portfolio.ecommerce.domain.model.DetalleOrden;
import com.portfolio.ecommerce.domain.model.Orden;
import com.portfolio.ecommerce.infrastructure.adapter.out.persistence.entity.DetalleOrdenEntity;
import com.portfolio.ecommerce.infrastructure.adapter.out.persistence.entity.OrdenEntity;
import com.portfolio.ecommerce.infrastructure.adapter.out.persistence.repository.OrdenJpaRepository;
import com.portfolio.ecommerce.infrastructure.adapter.out.persistence.repository.ProductoJpaRepository;
import com.portfolio.ecommerce.infrastructure.adapter.out.persistence.repository.UsuarioJpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Las lecturas son @Transactional(readOnly) porque al convertir a dominio se recorre la
 * colección lazy de líneas: necesita una sesión de Hibernate abierta (open-in-view está
 * desactivado a propósito).
 */
@Component
@Transactional(readOnly = true)
public class OrdenPersistenceAdapter implements OrdenRepositoryPort {

    private final OrdenJpaRepository ordenes;
    private final UsuarioJpaRepository usuarios;
    private final ProductoJpaRepository productos;

    public OrdenPersistenceAdapter(OrdenJpaRepository ordenes, UsuarioJpaRepository usuarios,
                                   ProductoJpaRepository productos) {
        this.ordenes = ordenes;
        this.usuarios = usuarios;
        this.productos = productos;
    }

    @Override
    @Transactional
    public Orden guardar(Orden orden) {
        OrdenEntity e = new OrdenEntity();
        e.setId(orden.getId());
        e.setNumero(orden.getNumero());
        e.setFechaCreacion(orden.getFechaCreacion());
        e.setFechaRecibida(orden.getFechaRecibida());
        e.setTotal(orden.getTotal());
        e.setUsuario(usuarios.getReferenceById(orden.getUsuario().getId()));

        for (DetalleOrden d : orden.getDetalle()) {
            DetalleOrdenEntity de = new DetalleOrdenEntity();
            de.setId(d.getId());
            de.setNombre(d.getNombre());
            de.setCantidad(d.getCantidad());
            de.setPrecio(d.getPrecio());
            de.setTotal(d.getTotal());
            de.setProducto(productos.getReferenceById(d.getProducto().getId()));
            de.setOrden(e);
            e.getDetalle().add(de);
        }
        return PersistenceMapper.aDominio(ordenes.save(e));
    }

    @Override
    public Optional<Orden> buscarPorId(Integer id) {
        return ordenes.findById(id).map(PersistenceMapper::aDominio);
    }

    @Override
    public List<Orden> buscarTodas() {
        return ordenes.findAll().stream().map(PersistenceMapper::aDominio).toList();
    }

    @Override
    public List<Orden> buscarPorUsuario(Integer idUsuario) {
        return ordenes.findByUsuario_Id(idUsuario).stream().map(PersistenceMapper::aDominio).toList();
    }
}
