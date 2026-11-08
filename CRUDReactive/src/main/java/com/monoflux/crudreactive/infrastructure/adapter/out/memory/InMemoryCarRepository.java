package com.monoflux.crudreactive.infrastructure.adapter.out.memory;

import com.monoflux.crudreactive.domain.model.Car;
import com.monoflux.crudreactive.domain.port.out.CarRepositoryPort;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Adaptador de salida: persistencia en memoria, thread-safe (sustituye a la lista estática del antiguo CarService).
 */
@Repository
public class InMemoryCarRepository implements CarRepositoryPort {

    private final Map<Integer, Car> cars = new ConcurrentSkipListMap<>();
    private final AtomicInteger sequence = new AtomicInteger();

    @Override
    public Mono<Car> save(Car car) {
        return Mono.fromSupplier(() -> {
            Car toStore = car.id() != null ? car : car.withId(nextFreeId());
            cars.put(toStore.id(), toStore);
            return toStore;
        });
    }

    @Override
    public Mono<Car> findById(Integer id) {
        return Mono.defer(() -> Mono.justOrEmpty(cars.get(id)));
    }

    @Override
    public Flux<Car> findByMarca(String marca) {
        return Flux.defer(() -> Flux.fromIterable(cars.values()))
                .filter(car -> car.marca() != null && car.marca().equalsIgnoreCase(marca));
    }

    @Override
    public Flux<Car> findAll() {
        return Flux.defer(() -> Flux.fromIterable(cars.values()));
    }

    @Override
    public Mono<Void> deleteById(Integer id) {
        return Mono.fromRunnable(() -> cars.remove(id));
    }

    private int nextFreeId() {
        int candidate;
        do {
            candidate = sequence.incrementAndGet();
        } while (cars.containsKey(candidate));
        return candidate;
    }
}
