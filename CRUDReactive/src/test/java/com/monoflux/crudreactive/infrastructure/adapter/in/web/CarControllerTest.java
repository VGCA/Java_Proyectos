package com.monoflux.crudreactive.infrastructure.adapter.in.web;

import com.monoflux.crudreactive.domain.exception.CarNotFoundException;
import com.monoflux.crudreactive.domain.model.Car;
import com.monoflux.crudreactive.domain.port.in.CarUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webflux.test.autoconfigure.WebFluxTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebFluxTest(CarController.class)
class CarControllerTest {

    private static final Car CAR = new Car(1, "firstMarca", "firstColor");

    @MockitoBean
    private CarUseCase carUseCase;

    @Autowired
    private WebTestClient webClient;

    @Test
    void saveCarReturnsCreated() {
        when(carUseCase.save(any(Car.class))).thenReturn(Mono.just(CAR));

        webClient.post().uri("/save")
                .bodyValue(CarDto.fromDomain(CAR))
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.marca").isEqualTo("firstMarca");

        verify(carUseCase, times(1)).save(CAR);
    }

    @Test
    void findCarByIdReturnsCar() {
        when(carUseCase.findById(1)).thenReturn(Mono.just(CAR));

        webClient.get().uri("/{id}", 1)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.marca").isEqualTo("firstMarca")
                .jsonPath("$.color").isEqualTo("firstColor");
    }

    @Test
    void findCarByIdReturnsNotFoundWhenMissing() {
        when(carUseCase.findById(99)).thenReturn(Mono.error(new CarNotFoundException(99)));

        webClient.get().uri("/{id}", 99)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void findCarByMarcaReturnsList() {
        when(carUseCase.findByMarca("firstMarca")).thenReturn(Flux.just(CAR));

        webClient.get().uri("/name/{name}", "firstMarca")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(CarDto.class).hasSize(1);
    }

    @Test
    void updateCarReturnsUpdatedCar() {
        when(carUseCase.update(any(Car.class))).thenReturn(Mono.just(CAR));

        webClient.put().uri("/update")
                .bodyValue(CarDto.fromDomain(CAR))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.color").isEqualTo("firstColor");
    }

    @Test
    void deleteCarReturnsOk() {
        when(carUseCase.delete(1)).thenReturn(Mono.empty());

        webClient.delete().uri("/delete/{id}", 1)
                .exchange()
                .expectStatus().isOk();

        verify(carUseCase, times(1)).delete(1);
    }
}
