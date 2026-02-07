package com.monoflux.crudreactive.controller;

import com.monoflux.crudreactive.model.Car;
import com.monoflux.crudreactive.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CarController
{
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCar(@RequestBody Car car) {
        carService.saveCar(car);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Mono<Car>> findCarById(@PathVariable("id") Integer id) {
        Mono<Car> car = carService.findCarById(id);
        HttpStatus status = (car != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(car, status);
    }

    @GetMapping(value = "/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Car> findCarByMarca(@PathVariable("name") String name) {
        return carService.findCarByMarca(name);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<Car> findAll() {
        return carService.findAll();
    }

    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Car> updateCar(@RequestBody Car e) {
        return carService.update(e);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Integer id) {
        carService.delete(id).subscribe();
    }
}
