package com.monoflux.crudreactive.infrastructure.adapter.in.web;

import com.monoflux.crudreactive.domain.port.in.CarUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CarController {

    private final CarUseCase carUseCase;

    public CarController(CarUseCase carUseCase) {
        this.carUseCase = carUseCase;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CarDto> saveCar(@RequestBody CarDto car) {
        return carUseCase.save(car.toDomain()).map(CarDto::fromDomain);
    }

    @GetMapping("/{id}")
    public Mono<CarDto> findCarById(@PathVariable("id") Integer id) {
        return carUseCase.findById(id).map(CarDto::fromDomain);
    }

    @GetMapping("/name/{name}")
    public Flux<CarDto> findCarByMarca(@PathVariable("name") String name) {
        return carUseCase.findByMarca(name).map(CarDto::fromDomain);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CarDto> findAll() {
        return carUseCase.findAll().map(CarDto::fromDomain);
    }

    @PutMapping("/update")
    public Mono<CarDto> updateCar(@RequestBody CarDto car) {
        return carUseCase.update(car.toDomain()).map(CarDto::fromDomain);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> delete(@PathVariable("id") Integer id) {
        return carUseCase.delete(id);
    }
}
