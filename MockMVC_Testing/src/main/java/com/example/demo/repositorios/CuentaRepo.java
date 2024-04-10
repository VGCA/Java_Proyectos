package com.example.demo.repositorios;

import com.example.demo.entidades.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepo extends JpaRepository<Cuenta,Long> {

    @Query("select c from Cuenta c where c.persona=?1")
    public Optional<Cuenta> findByPersona(String persona);
}
