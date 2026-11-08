package com.monoflux.crudreactive.application.service;

import com.monoflux.crudreactive.domain.exception.CarNotFoundException;
import com.monoflux.crudreactive.domain.model.Car;
import com.monoflux.crudreactive.domain.port.in.CarUseCase;
import com.monoflux.crudreactive.domain.port.out.CarRepositoryPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CarService implements CarUseCase {

    private final CarRepositoryPort carRepository;

    public CarService(CarRepositoryPort carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Mono<Car> save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Mono<Car> findById(Integer id) {
        return carRepository.findById(id)
                .switchIfEmpty(Mono.error(() -> new CarNotFoundException(id)));
    }

    @Override
    public Flux<Car> findByMarca(String marca) {
        return carRepository.findByMarca(marca);
    }

    @Override
    public Flux<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Mono<Car> update(Car car) {
        if (car.id() == null) {
            return Mono.error(new IllegalArgumentException("The id is required to update a car"));
        }
        return findById(car.id()).flatMap(existing -> carRepository.save(car));
    }

    @Override
    public Mono<Void> delete(Integer id) {
        return carRepository.deleteById(id);
    }
}
