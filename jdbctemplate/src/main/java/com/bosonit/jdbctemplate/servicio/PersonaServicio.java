package com.bosonit.jdbctemplate.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bosonit.jdbctemplate.modelo.Persona;

@Service
public class PersonaServicio {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int guardarPersona(Persona e) {
        String query = "insert into persona values('" + e.getId() + "','" + e.getNombre() + "','" + e.getApellido()
                + "')";
        return jdbcTemplate.update(query);
    }

    public int updatePersona(Persona e) {
        String query = "update persona set nombre='" + e.getNombre() + "',apellido='" + e.getApellido() + "' where id='"
                + e.getId() + "' ";
        return jdbcTemplate.update(query);
    }

    public int borrarPersona(Persona e) {
        String query = "delete from persona where id='" + e.getId() + "' ";
        return jdbcTemplate.update(query);
    }

    public List<Persona> findAll() {

        String sql = "SELECT * FROM persona";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Persona(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido")));
    }
}
