package com.portfolio.eCommerce.servicio;

import com.portfolio.eCommerce.modelo.Orden;
import com.portfolio.eCommerce.modelo.Usuario;

import java.util.List;
import java.util.Optional;

public interface OrdenServicio {

    Orden save(Orden orden);
    List<Orden> findAll();
    String generarNumeroOrden();
    List<Orden> findByUsuario(Usuario usuario);
    Optional<Orden> findById(Integer id);
}
