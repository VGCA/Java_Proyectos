package com.bosonit.crudreactivo;

import com.bosonit.crudreactivo.controlador.PersonaController;
import com.bosonit.crudreactivo.modelo.Persona;
import com.bosonit.crudreactivo.servicio.PersonaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

@WebFluxTest(controllers = PersonaController.class)
class PersonaControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PersonaService personaService;

    @Test
    void testVerPersonas_Reactive() {
        Persona p1 = new Persona(1, "Alice");
        Persona p2 = new Persona(2, "Bob");

        Mockito.when(personaService.verPersonas()).thenReturn(Flux.just(p1, p2));

        webTestClient.get().uri("/verlista")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Persona.class)
                .hasSize(2)
                .contains(p1, p2);
    }

    @Test
    void agregarPersonaReactiveTest() {
        Persona persona = new Persona(1, "Charlie");
        Mockito.when(personaService.ingresarPersona(any())).thenReturn(Mono.just(persona));

        webTestClient.post().uri("/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(persona)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Persona.class)
                .isEqualTo(persona);
    }
}