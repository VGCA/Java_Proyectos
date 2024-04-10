package com.bosonit.CRUDrelaciones.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="estudiante_asignatura")
public record EstudianteAsignatura(String id_asignatura, String id_student, String asignatura, String coments,
                                   Date initial_date, Date finish_date) {}
