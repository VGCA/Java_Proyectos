package com.portfolio.ecommerce.infrastructure.adapter.out.persistence;

import com.portfolio.ecommerce.domain.model.DetalleOrden;
import com.portfolio.ecommerce.domain.model.Orden;
import com.portfolio.ecommerce.domain.model.Producto;
import com.portfolio.ecommerce.domain.model.Usuario;
import com.portfolio.ecommerce.infrastructure.adapter.out.persistence.entity.DetalleOrdenEntity;
import com.portfolio.ecommerce.infrastructure.adapter.out.persistence.entity.OrdenEntity;
import com.portfolio.ecommerce.infrastructure.adapter.out.persistence.entity.ProductoEntity;
import com.portfolio.ecommerce.infrastructure.adapter.out.persistence.entity.UsuarioEntity;

/**
 * Traduce entre el modelo de dominio y las entidades JPA. Es el "muro" que evita
 * que anotaciones de JPA (o proxies de Hibernate) se filtren al dominio.
 * Siempre usa getters: así funciona también con proxies lazy de Hibernate.
 */
final class PersistenceMapper {

    private PersistenceMapper() {
    }

    static Usuario aDominio(UsuarioEntity e) {
        if (e == null) {
            return null;
        }
        Usuario u = new Usuario();
        u.setId(e.getId());
        u.setNombre(e.getNombre());
        u.setUsername(e.getUsername());
        u.setEmail(e.getEmail());
        u.setDireccion(e.getDireccion());
        u.setTelefono(e.getTelefono());
        u.setTipo(e.getTipo());
        u.setPassword(e.getPassword());
        return u;
    }

    static UsuarioEntity aEntidad(Usuario u) {
        UsuarioEntity e = new UsuarioEntity();
        e.setId(u.getId());
        e.setNombre(u.getNombre());
        e.setUsername(u.getUsername());
        e.setEmail(u.getEmail());
        e.setDireccion(u.getDireccion());
        e.setTelefono(u.getTelefono());
        e.setTipo(u.getTipo());
        e.setPassword(u.getPassword());
        return e;
    }

    static Producto aDominio(ProductoEntity e) {
        if (e == null) {
            return null;
        }
        Producto p = new Producto();
        p.setId(e.getId());
        p.setNombre(e.getNombre());
        p.setDescripcion(e.getDescripcion());
        p.setImagen(e.getImagen());
        p.setPrecio(e.getPrecio());
        p.setCantidad(e.getCantidad());
        p.setUsuario(aDominio(e.getUsuario()));
        return p;
    }

    static ProductoEntity aEntidad(Producto p, UsuarioEntity propietario) {
        ProductoEntity e = new ProductoEntity();
        e.setId(p.getId());
        e.setNombre(p.getNombre());
        e.setDescripcion(p.getDescripcion());
        e.setImagen(p.getImagen());
        e.setPrecio(p.getPrecio());
        e.setCantidad(p.getCantidad());
        e.setUsuario(propietario);
        return e;
    }

    static DetalleOrden aDominio(DetalleOrdenEntity e) {
        DetalleOrden d = new DetalleOrden();
        d.setId(e.getId());
        d.setNombre(e.getNombre());
        d.setCantidad(e.getCantidad());
        d.setPrecio(e.getPrecio());
        d.setTotal(e.getTotal());
        d.setProducto(aDominio(e.getProducto()));
        return d;
    }

    static Orden aDominio(OrdenEntity e) {
        Orden o = new Orden();
        o.setId(e.getId());
        o.setNumero(e.getNumero());
        o.setFechaCreacion(e.getFechaCreacion());
        o.setFechaRecibida(e.getFechaRecibida());
        o.setTotal(e.getTotal());
        o.setUsuario(aDominio(e.getUsuario()));
        e.getDetalle().forEach(d -> o.getDetalle().add(aDominio(d)));
        return o;
    }
}
