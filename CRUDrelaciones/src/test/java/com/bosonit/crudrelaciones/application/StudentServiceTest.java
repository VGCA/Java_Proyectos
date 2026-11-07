package com.bosonit.crudrelaciones.application;

import com.bosonit.crudrelaciones.application.service.StudentService;
import com.bosonit.crudrelaciones.domain.model.Student;
import com.bosonit.crudrelaciones.domain.port.out.StudentRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepositoryPort repositorio;

    @InjectMocks
    private StudentService service;

    private final Student student = new Student(1, "Juan", "p1", "ok", "pr1");

    @Test
    void listarAlumnos_devuelveLosDelPuerto() {
        when(repositorio.findAll()).thenReturn(List.of(student));
        assertEquals(List.of(student), service.listarAlumnos());
    }

    @Test
    void listarPorId_devuelveOptional() {
        when(repositorio.findById(1)).thenReturn(Optional.of(student));
        assertTrue(service.listarPorId(1).isPresent());
    }

    @Test
    void guardarEstudiante_delegaEnPuerto() {
        when(repositorio.save(student)).thenReturn(student);
        assertEquals(student, service.guardarEstudiante(student));
    }

    @Test
    void borrarEstudiante_delegaEnPuerto() {
        service.borrarEstudiante(1);
        verify(repositorio).deleteById(1);
    }
}
