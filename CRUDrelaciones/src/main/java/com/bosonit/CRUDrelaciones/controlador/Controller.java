package com.bosonit.CRUDrelaciones.controlador;

import com.bosonit.CRUDrelaciones.modelo.Student;
import com.bosonit.CRUDrelaciones.servicio.ServicioEstudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
@RequestMapping
public class Controller {

    @Autowired
    private ServicioEstudiante servicioEstudiante;

    /**
     * FUNCION QUE BORRA UN ESTUDIANTE
     * POR SU ID
     * @param id del estudiante a borrar
     * @return mensaje de exito o no
     */
    @DeleteMapping("/borrar/{id}")
    public String borrarEstudiante(@PathVariable String id){
        String dato = "Error al eliminar el estudiante";
        if(id!=null){
            servicioEstudiante.borrarEstudiante(id);
            dato = "Eliminado con éxito";
        }
        return dato;
    }

    /**
     * FUNCION QUE RETORNA LA LISTA ENTERA
     * DE ESTUDIANTES
     * @return lista de estudiantes
     */
    @GetMapping("/estudiantes")
    public List<Student> listarEstudiantes(){
        return servicioEstudiante.listarAlumnos();
    }

    /**
     * FUNCION QUE AÑADE UN NUEVO ESTUDIANTE
     * A LA LISTA
     * @param estudiante a añadir
     * @return si fue un exito o no
     */
    @PostMapping("/agregarEstudiante")
    public String agregarEstudiante(Student estudiante){
        String respuesta = "Hubo un error";
        if(!estudiante.equals(null)){
            servicioEstudiante.guardarEstudiante(estudiante);
            respuesta = "Añadido con éxito";
        }
        return respuesta;
    }

    /**
     * FUNCION QUE RETORNA EL ESTUDIANTE
     * BUSCADO POR SU ID
     * @param id por el que se quiere buscar
     * @return estudiante encontrado
     */
    @GetMapping("/buscar/{id}")
    public Optional<Student> buscarEstudianteId(@PathVariable String id){
        Optional<Student> buscarEstudiante = servicioEstudiante.listarPorId(id);
        return buscarEstudiante;
    }
}
