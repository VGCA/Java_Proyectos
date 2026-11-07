package com.bosonit.crudrelaciones.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "profesor")
public class ProfesorEntity {

    @Id
    private Integer id;
    private String idProfesor;
    private String idPersona;
    private String coments;
    private String branch;

    protected ProfesorEntity() {
        // requerido por JPA
    }

    public ProfesorEntity(Integer id, String idProfesor, String idPersona, String coments, String branch) {
        this.id = id;
        this.idProfesor = idProfesor;
        this.idPersona = idPersona;
        this.coments = coments;
        this.branch = branch;
    }

    public Integer getId() { return id; }
    public String getIdProfesor() { return idProfesor; }
    public String getIdPersona() { return idPersona; }
    public String getComents() { return coments; }
    public String getBranch() { return branch; }
}
