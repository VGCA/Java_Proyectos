package com.curso.infraestructura.adaptador.salida.persistencia;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.curso.aplicacion.puerto.salida.RepositorioPedidosPort;
import com.curso.dominio.modelo.Pedido;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class PedidoPersistenciaAdapter implements RepositorioPedidosPort {

    private final PedidoJpaRepository jpa;

    @PersistenceContext
    private EntityManager em;

    public PedidoPersistenciaAdapter(PedidoJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    @Transactional
    public Pedido guardar(Pedido pedido) {
        PedidoEntidad guardado = jpa.save(PedidoEntidadMapper.aEntidad(pedido, em));
        return PedidoEntidadMapper.aDominio(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pedido> buscarPorId(Integer id) {
        return jpa.findById(id).map(PedidoEntidadMapper::aDominio);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> buscarTodos() {
        return jpa.findAll().stream().map(PedidoEntidadMapper::aDominio).toList();
    }

}
