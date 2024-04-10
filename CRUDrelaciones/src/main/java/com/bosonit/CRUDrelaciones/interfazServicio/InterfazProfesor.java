package com.bosonit.CRUDrelaciones.interfazServicio;

import com.bosonit.CRUDrelaciones.modelo.Profesor;

import java.util.List;
import java.util.Optional;

public interface InterfazProfesor {
    public List<Profesor> listarProfesores();
    public Optional<Profesor> listarPorId(String id);
    public int guardarProfesor(Profesor profesor);
    public void borrarProfesor(String id);
}
