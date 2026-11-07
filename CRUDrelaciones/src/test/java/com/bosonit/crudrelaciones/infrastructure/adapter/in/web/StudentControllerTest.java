package com.bosonit.crudrelaciones.infrastructure.adapter.in.web;

import com.bosonit.crudrelaciones.domain.model.Student;
import com.bosonit.crudrelaciones.domain.port.in.StudentUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentUseCase studentUseCase;

    private final Student student = new Student(1, "Juan", "p1", "ok", "pr1");

    @Test
    void listarEstudiantes_debeRetornarLista() throws Exception {
        when(studentUseCase.listarAlumnos()).thenReturn(List.of(student));

        mockMvc.perform(get("/estudiantes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name_student").value("Juan"));
    }

    @Test
    void buscarEstudianteId_debeRetornarEstudiante() throws Exception {
        when(studentUseCase.listarPorId(1)).thenReturn(Optional.of(student));

        mockMvc.perform(get("/buscar/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void borrarEstudiante_debeRetornarExito() throws Exception {
        mockMvc.perform(delete("/borrar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Eliminado con éxito"));

        verify(studentUseCase).borrarEstudiante(1);
    }

    @Test
    void agregarEstudiante_debeRetornarMensajeExito() throws Exception {
        mockMvc.perform(post("/agregarEstudiante")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name_student\":\"Juan\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Añadido con éxito"));

        verify(studentUseCase).guardarEstudiante(new Student(1, "Juan", null, null, null));
    }
}
