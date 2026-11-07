package com.bosonit.crudrelaciones.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class StudentEntity {

    @Id
    private Integer id;
    private String nameStudent;
    private String idPersona;
    private String coments;
    private String idProfesor;

    protected StudentEntity() {
        // requerido por JPA
    }

    public StudentEntity(Integer id, String nameStudent, String idPersona, String coments, String idProfesor) {
        this.id = id;
        this.nameStudent = nameStudent;
        this.idPersona = idPersona;
        this.coments = coments;
        this.idProfesor = idProfesor;
    }

    public Integer getId() { return id; }
    public String getNameStudent() { return nameStudent; }
    public String getIdPersona() { return idPersona; }
    public String getComents() { return coments; }
    public String getIdProfesor() { return idProfesor; }
}
