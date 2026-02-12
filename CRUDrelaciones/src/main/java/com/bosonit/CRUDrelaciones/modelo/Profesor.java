package com.bosonit.crudrelaciones.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public record Profesor(@Id Integer id, String id_profesor, String id_persona, String coments, String branch) {}
