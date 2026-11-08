package com.monoflux.crudreactive.domain.port.in;

import com.monoflux.crudreactive.domain.model.Car;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Puerto de entrada (driving port): lo que la aplicación ofrece al exterior.
 */
public interface CarUseCase {

    Mono<Car> save(Car car);

    Mono<Car> findById(Integer id);

    Flux<Car> findByMarca(String marca);

    Flux<Car> findAll();

    Mono<Car> update(Car car);

    Mono<Void> delete(Integer id);
}
