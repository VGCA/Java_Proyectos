package com.monoflux.CRUDReactive.repository;

import com.monoflux.CRUDReactive.model.Car;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CarRepository{
    Mono<Car> saveCar(Car car);
    Mono<Car> findCarById(Integer id);
    Mono<Car> update(Car car);
    Flux<Car> findCarByMarca(String marca);
    Flux<Car> findAll();
    Mono<Void> delete(Integer id);
}
