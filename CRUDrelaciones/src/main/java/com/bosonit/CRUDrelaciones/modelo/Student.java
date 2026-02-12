package com.bosonit.crudrelaciones.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public record Student(@Id Integer id, String name_student, String id_persona, String coments, String id_profesor){}
