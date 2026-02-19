package com.bosonit.juego4enraya;

import com.bosonit.juego4enraya.controlador.PersonaController;
import com.bosonit.juego4enraya.modelo.Persona;
import com.bosonit.juego4enraya.repositorio.PersonaRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonaControllerTest {

    @Mock
    private PersonaRepo personaRepo;

    @InjectMocks
    private PersonaController personaController;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(personaController).build();
    }

    @Test
    void obtenerPersonas_ShouldReturnFluxOfPersonas() {
        // Arrange
        Persona p1 = new Persona(1, "Alice");
        Persona p2 = new Persona(2, "Bob");
        when(personaRepo.findAll()).thenReturn(Flux.just(p1, p2));

        // Act & Assert
        webTestClient.get()
                .uri("/")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Persona.class)
                .hasSize(2)
                .contains(p1, p2);
    }

    @Test
    void agregarPersona_ShouldReturnSavedPersona() {
        // Arrange
        Persona inputPersona = new Persona(3, "Charlie");
        Persona savedPersona = new Persona(3, "Charlie");

        when(personaRepo.save(any(Persona.class))).thenReturn(Mono.just(savedPersona));

        // Act & Assert
        webTestClient.post()
                .uri("/añadir")
                .bodyValue(inputPersona)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Persona.class)
                .isEqualTo(savedPersona);
    }
}