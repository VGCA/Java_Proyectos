package com.portfolio.ecommerce.repositorio;

import com.portfolio.ecommerce.modelo.Orden;
import com.portfolio.ecommerce.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenRepo extends JpaRepository<Orden,Integer> {

    List<Orden> findByUsuario(Usuario usuario);
}
