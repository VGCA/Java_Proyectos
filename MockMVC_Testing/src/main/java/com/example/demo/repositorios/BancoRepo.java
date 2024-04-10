package com.example.demo.repositorios;

import com.example.demo.entidades.Banco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BancoRepo extends JpaRepository<Banco,Long> {
}
