package com.bosonit.CRUDrelaciones.modelo;

import javax.persistence.*;

@Entity
@Table(name="estudiante")
public record Student(String id_student, String id_persona, String coments, String id_profesor){}
