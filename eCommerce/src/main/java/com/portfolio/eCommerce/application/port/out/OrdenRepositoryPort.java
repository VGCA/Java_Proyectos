package com.portfolio.ecommerce.application.port.out;

import com.portfolio.ecommerce.domain.model.Orden;

import java.util.List;
import java.util.Optional;

public interface OrdenRepositoryPort {

    Orden guardar(Orden orden);

    Optional<Orden> buscarPorId(Integer id);

    List<Orden> buscarTodas();

    List<Orden> buscarPorUsuario(Integer idUsuario);
}
