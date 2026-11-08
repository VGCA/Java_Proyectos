package com.monoflux.crudreactive.domain.port.out;

import com.monoflux.crudreactive.domain.model.Car;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Puerto de salida (driven port): lo que la aplicación necesita de la persistencia.
 */
public interface CarRepositoryPort {

    Mono<Car> save(Car car);

    Mono<Car> findById(Integer id);

    Flux<Car> findByMarca(String marca);

    Flux<Car> findAll();

    Mono<Void> deleteById(Integer id);
}
