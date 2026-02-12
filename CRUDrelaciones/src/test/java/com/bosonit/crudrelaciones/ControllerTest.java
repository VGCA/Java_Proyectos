package com.bosonit.crudrelaciones;

import com.bosonit.crudrelaciones.controlador.Controller;
import com.bosonit.crudrelaciones.modelo.Student;
import com.bosonit.crudrelaciones.servicio.ServicioEstudiante;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Controller.class) // Esto soluciona el error de "valid Spring bean"
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc; // Ahora se reconocerá correctamente

    @MockBean
    private ServicioEstudiante servicioEstudiante; // Mockeamos la capa de servicio

    @Test
    void listarEstudiantes_DebeRetornarLista() throws Exception {
        Student s1 = new Student(); // Asume que tienes un constructor o setters
        when(servicioEstudiante.listarAlumnos()).thenReturn(Arrays.asList(s1));

        mockMvc.perform(get("/estudiantes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void buscarEstudianteId_DebeRetornarEstudiante() throws Exception {
        Student s = new Student();
        when(servicioEstudiante.listarPorId(1)).thenReturn(Optional.of(s));

        mockMvc.perform(get("/buscar/1"))
                .andExpect(status().isOk())
                .andExpect(json_is_present()); // Verifica que el JSON no esté vacío
    }

    @Test
    void borrarEstudiante_DebeRetornarExito() throws Exception {
        // No necesitamos que el servicio haga nada (es void), solo que no falle
        mockMvc.perform(delete("/borrar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Eliminado con éxito"));
    }

    @Test
    void agregarEstudiante_DebeRetornarMensajeExito() throws Exception {
        // Para que Spring pueda mapear el objeto Student desde el cuerpo de la petición
        // es recomendable usar @RequestBody en el controlador original
        mockMvc.perform(post("/agregarEstudiante")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Juan\"}")) // JSON de ejemplo
                .andExpect(status().isOk())
                .andExpect(content().string("Añadido con éxito"));
    }

    // Método auxiliar para legibilidad
    private org.springframework.test.web.servlet.ResultMatcher json_is_present() {
        return jsonPath("$").exists();
    }
}
