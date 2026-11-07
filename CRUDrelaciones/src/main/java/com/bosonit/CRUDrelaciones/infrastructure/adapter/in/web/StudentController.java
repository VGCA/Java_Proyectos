package com.bosonit.crudrelaciones.infrastructure.adapter.in.web;

import com.bosonit.crudrelaciones.domain.model.Student;
import com.bosonit.crudrelaciones.domain.port.in.StudentUseCase;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    private final StudentUseCase studentUseCase;

    public StudentController(StudentUseCase studentUseCase) {
        this.studentUseCase = studentUseCase;
    }

    @DeleteMapping("/borrar/{id}")
    public String borrarEstudiante(@PathVariable Integer id) {
        studentUseCase.borrarEstudiante(id);
        return "Eliminado con éxito";
    }

    @GetMapping("/estudiantes")
    public List<Student> listarEstudiantes() {
        return studentUseCase.listarAlumnos();
    }

    @PostMapping("/agregarEstudiante")
    public String agregarEstudiante(@RequestBody Student estudiante) {
        studentUseCase.guardarEstudiante(estudiante);
        return "Añadido con éxito";
    }

    @GetMapping("/buscar/{id}")
    public Optional<Student> buscarEstudianteId(@PathVariable Integer id) {
        return studentUseCase.listarPorId(id);
    }
}
