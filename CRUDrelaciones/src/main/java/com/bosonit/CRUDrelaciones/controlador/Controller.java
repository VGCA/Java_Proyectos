package com.bosonit.crudrelaciones.controlador;

import com.bosonit.crudrelaciones.modelo.Student;
import com.bosonit.crudrelaciones.servicio.ServicioEstudiante;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping
public class Controller {

    private final ServicioEstudiante servicioEstudiante;

    public Controller(ServicioEstudiante servicioEstudiante) {
        this.servicioEstudiante = servicioEstudiante;
    }

    @DeleteMapping("/borrar/{id}")
    public String borrarEstudiante(@PathVariable Integer id){
        String dato = "Error al eliminar el estudiante";
        if(id!=null){
            servicioEstudiante.borrarEstudiante(id);
            dato = "Eliminado con éxito";
        }
        return dato;
    }

    @GetMapping("/estudiantes")
    public List<Student> listarEstudiantes(){
        return servicioEstudiante.listarAlumnos();
    }

    @PostMapping("/agregarEstudiante")
    public String agregarEstudiante(Student estudiante){
        String respuesta = "Hubo un error";
        if(!estudiante.equals(null)){
            servicioEstudiante.guardarEstudiante(estudiante);
            respuesta = "Añadido con éxito";
        }
        return respuesta;
    }

    @GetMapping("/buscar/{id}")
    public Optional<Student> buscarEstudianteId(@PathVariable Integer id){
        return servicioEstudiante.listarPorId(id);
    }
}