package com.portfolio.eCommerce.repositorio;

import com.portfolio.eCommerce.modelo.Orden;
import com.portfolio.eCommerce.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenRepo extends JpaRepository<Orden,Integer> {

    List<Orden> findByUsuario(Usuario usuario);
}
