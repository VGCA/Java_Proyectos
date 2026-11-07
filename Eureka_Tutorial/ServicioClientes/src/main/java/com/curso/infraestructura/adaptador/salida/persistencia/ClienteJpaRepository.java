package com.curso.infraestructura.adaptador.salida.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteJpaRepository extends JpaRepository<ClienteEntidad, String> {

}
