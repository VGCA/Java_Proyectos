package com.bosonit.CRUDrelaciones.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="profesor")
public record Profesor(String id_profesor, String id_persona, String coments, String branch) {}
