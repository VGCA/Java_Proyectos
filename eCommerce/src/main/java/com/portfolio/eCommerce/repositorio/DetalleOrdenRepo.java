package com.portfolio.eCommerce.repositorio;

import com.portfolio.eCommerce.modelo.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleOrdenRepo extends JpaRepository<DetalleOrden,Integer> {
}
