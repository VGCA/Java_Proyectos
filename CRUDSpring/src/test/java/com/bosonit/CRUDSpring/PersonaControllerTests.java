package com.bosonit.crudspring;

import com.bosonit.crudspring.controlador.PersonaController;
import com.bosonit.crudspring.interfacesservice.InterfazPersonaServicio;
import com.bosonit.crudspring.modelo.Persona;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebMvcTest(PersonaController.class)
class PersonaControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private InterfazPersonaServicio servicio;

	@Autowired
	private ObjectMapper objectMapper;

	private Persona crearPersonaTest(){
		return new Persona(1,"test","123",
				"test","testCompany","testEmail","testCity");
	}

	@Test
	void testListarPersonas() throws Exception {
		List<Persona> lista = List.of(crearPersonaTest());
		when(servicio.listarPersonas()).thenReturn(lista);

		mockMvc.perform(get("/listar"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(2));
	}

	@Test
	void testNuevaPersona() throws Exception {
		Persona p = crearPersonaTest();

		mockMvc.perform(post("/agregar")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(p)))
				.andExpect(status().isOk());

		verify(servicio, times(1)).guardarPersona(any(Persona.class));
	}

	@Test
	void testListarPorId() throws Exception {
		Persona p = crearPersonaTest();
		when(servicio.listarPorId(1)).thenReturn(List.of(p));

		mockMvc.perform(get("/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nombre").value("test"));
	}
}
