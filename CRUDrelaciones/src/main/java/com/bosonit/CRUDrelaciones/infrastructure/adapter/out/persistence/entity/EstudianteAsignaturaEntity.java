package com.bosonit.crudrelaciones.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "estudiante_asignatura")
public class EstudianteAsignaturaEntity {

    @Id
    private String idAsignatura;
    private String idStudent;
    private String asignatura;
    private String coments;
    private Date initialDate;
    private Date finishDate;

    protected EstudianteAsignaturaEntity() {
        // requerido por JPA
    }

    public EstudianteAsignaturaEntity(String idAsignatura, String idStudent, String asignatura, String coments,
                                      Date initialDate, Date finishDate) {
        this.idAsignatura = idAsignatura;
        this.idStudent = idStudent;
        this.asignatura = asignatura;
        this.coments = coments;
        this.initialDate = initialDate;
        this.finishDate = finishDate;
    }

    public String getIdAsignatura() { return idAsignatura; }
    public String getIdStudent() { return idStudent; }
    public String getAsignatura() { return asignatura; }
    public String getComents() { return coments; }
    public Date getInitialDate() { return initialDate; }
    public Date getFinishDate() { return finishDate; }
}
