package com.portfolio.ecommerce.repositorio;

import com.portfolio.ecommerce.modelo.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleOrdenRepo extends JpaRepository<DetalleOrden,Integer> {
}
