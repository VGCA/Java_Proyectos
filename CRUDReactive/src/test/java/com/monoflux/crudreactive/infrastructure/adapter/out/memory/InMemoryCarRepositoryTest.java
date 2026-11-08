package com.monoflux.crudreactive.infrastructure.adapter.out.memory;

import com.monoflux.crudreactive.domain.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class InMemoryCarRepositoryTest {

    private InMemoryCarRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryCarRepository();
    }

    @Test
    void saveAndFindById() {
        Car car = new Car(1, "Seat", "rojo");

        StepVerifier.create(repository.save(car).then(repository.findById(1)))
                .expectNext(car)
                .verifyComplete();
    }

    @Test
    void saveGeneratesIdWhenMissing() {
        StepVerifier.create(repository.save(new Car(null, "Seat", "rojo")))
                .expectNextMatches(saved -> saved.id() != null && "Seat".equals(saved.marca()))
                .verifyComplete();
    }

    @Test
    void findByIdIsEmptyWhenMissing() {
        StepVerifier.create(repository.findById(42)).verifyComplete();
    }

    @Test
    void findByMarcaIgnoresCase() {
        Car seat = new Car(1, "Seat", "rojo");
        Car audi = new Car(2, "Audi", "negro");

        StepVerifier.create(repository.save(seat).then(repository.save(audi)).thenMany(repository.findByMarca("seat")))
                .expectNext(seat)
                .verifyComplete();
    }

    @Test
    void findAllReturnsCarsOrderedById() {
        Car first = new Car(1, "Seat", "rojo");
        Car second = new Car(2, "Audi", "negro");

        StepVerifier.create(repository.save(second).then(repository.save(first)).thenMany(repository.findAll()))
                .expectNext(first, second)
                .verifyComplete();
    }

    @Test
    void deleteByIdRemovesCar() {
        Car car = new Car(1, "Seat", "rojo");

        StepVerifier.create(repository.save(car).then(repository.deleteById(1)).then(repository.findById(1)))
                .verifyComplete();
    }
}
