package com.curso.infraestructura.adaptador.salida.persistencia;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoJpaRepository extends JpaRepository<PedidoEntidad, Integer> {

    /** Carga los detalles en la misma consulta (evita una consulta extra por pedido al mapear a dominio). */
    @Override
    @EntityGraph("PedidoConDetalles")
    List<PedidoEntidad> findAll();

    @Override
    @EntityGraph("PedidoConDetalles")
    Optional<PedidoEntidad> findById(Integer id);

}
