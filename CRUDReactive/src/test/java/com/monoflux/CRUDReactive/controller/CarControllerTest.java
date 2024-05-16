package com.monoflux.CRUDReactive.controller;

import com.monoflux.CRUDReactive.model.Car;
import com.monoflux.CRUDReactive.repository.CarRepository;
import com.monoflux.CRUDReactive.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CarController.class)
@Import(CarService.class)
public class CarControllerTest {

    @MockBean
    CarRepository repository;

    @Autowired
    private WebTestClient webClient;

    @Test
    void testCreateEmployee() {
        Car car = new Car();
        car.setId(1);
        car.setMarca("firstMarca");
        car.setColor("firstColor");


        Mockito.when(repository.saveCar(car)).thenReturn(Mono.just(car));

        webClient.post()
                .uri("/save")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(car))
                .exchange()
                .expectStatus().isCreated();

        Mockito.verify(repository, times(1)).saveCar(car);
    }

    @Test
    void testGetCarsByName() {
        Car car = new Car();
        car.setId(1);
        car.setMarca("firstMarca");
        car.setColor("firstColor");

        List<Car> list = new ArrayList<Car>();
        list.add(car);

        Flux<Car> carFlux = Flux.fromIterable(list);

        Mockito
                .when(repository.findCarByMarca("firstMarca"))
                .thenReturn(carFlux);

        webClient.get().uri("/name/{name}", "firstMarca")
                .header(HttpHeaders.ACCEPT, "application/json")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Car.class);

        Mockito.verify(repository, times(1)).findCarByMarca("firstMarca");
    }

    @Test
    void testGetCarById() {
        Car car = new Car();
        car.setId(100);
        car.setMarca("firstMarca");
        car.setColor("firstColor");

        Mockito
                .when(repository.findCarById(100))
                .thenReturn(Mono.just(car));

        webClient.get().uri("/{id}", 100)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(100)
                .jsonPath("$.marca").isEqualTo("firstMarca")
                .jsonPath("$.color").isEqualTo("firstColor");

        Mockito.verify(repository, times(1)).findCarById(100);
    }

    @Test
    void testDeleteCar() {
        Mono<Void> voidReturn = Mono.empty();
        Mockito
                .when(repository.delete(1))
                .thenReturn(voidReturn);

        webClient.get().uri("/delete/{id}", 1)
                .exchange()
                .expectStatus().isOk();
    }
}

