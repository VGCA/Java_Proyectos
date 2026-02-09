package com.bosonit.crudspring.modelo;

import javax.persistence.*;

@Entity
@Table(name="persona")
public class Persona {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idPersona;

    String usuario;
    String password;
    String name;
    String companyEmail;
    String personalEmail;
    String city;

    public Persona(){}

    public Persona(int idPersona, String usuario, String password, String name,
                   String companyEmail, String personalEmail,
                   String city) {
        this.idPersona = idPersona;
        this.usuario = usuario;
        this.password = password;
        this.name = name;
        this.companyEmail = companyEmail;
        this.personalEmail = personalEmail;
        this.city = city;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
}
