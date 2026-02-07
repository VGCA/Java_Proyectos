package com.monoflux.crudreactive.service;

import com.monoflux.crudreactive.model.Car;
import com.monoflux.crudreactive.repository.CarRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService implements CarRepository {

    private static final List<Car> listCar = new ArrayList<>();

    @Override
    public Mono<Car> saveCar(Car car) {
        listCar.add(car);
        return Mono.just(car);
    }

    @Override
    public Mono<Car> findCarById(Integer id) {
        Optional<Car> firstCarFind = listCar.stream().filter(car -> car.getId().equals(id))
                .findFirst();
        return Mono.justOrEmpty(firstCarFind);
    }

    @Override
    public Mono<Car> update(Car car) {
        return null;
    }

    @Override
    public Flux<Car> findCarByMarca(String marca) {
        return null;
    }

    @Override
    public Flux<Car> findAll() {
        return Flux.fromIterable(listCar);
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return null;
    }
}
