package com.bosonit.gestionusuarios.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bosonit.gestionusuarios.modelo.Empleado;

@Repository
public interface EmpleadoRepo extends JpaRepository<Empleado, Integer>{
    
}
