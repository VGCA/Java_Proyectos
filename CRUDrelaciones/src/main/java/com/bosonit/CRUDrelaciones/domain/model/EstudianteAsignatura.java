package com.bosonit.crudrelaciones.domain.model;

import java.util.Date;

public record EstudianteAsignatura(String id_asignatura, String id_student, String asignatura, String coments,
                                   Date initial_date, Date finish_date) {
}
