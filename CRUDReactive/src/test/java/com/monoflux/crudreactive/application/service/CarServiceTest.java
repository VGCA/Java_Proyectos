package com.monoflux.crudreactive.application.service;

import com.monoflux.crudreactive.domain.exception.CarNotFoundException;
import com.monoflux.crudreactive.domain.model.Car;
import com.monoflux.crudreactive.domain.port.out.CarRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    private static final Car CAR = new Car(1, "firstMarca", "firstColor");

    @Mock
    private CarRepositoryPort repository;

    @InjectMocks
    private CarService service;

    @Test
    void saveDelegatesToRepository() {
        when(repository.save(CAR)).thenReturn(Mono.just(CAR));

        StepVerifier.create(service.save(CAR)).expectNext(CAR).verifyComplete();
    }

    @Test
    void findByIdReturnsCar() {
        when(repository.findById(1)).thenReturn(Mono.just(CAR));

        StepVerifier.create(service.findById(1)).expectNext(CAR).verifyComplete();
    }

    @Test
    void findByIdFailsWhenCarDoesNotExist() {
        when(repository.findById(99)).thenReturn(Mono.empty());

        StepVerifier.create(service.findById(99))
                .expectError(CarNotFoundException.class)
                .verify();
    }

    @Test
    void findByMarcaReturnsMatchingCars() {
        when(repository.findByMarca("firstMarca")).thenReturn(Flux.just(CAR));

        StepVerifier.create(service.findByMarca("firstMarca")).expectNext(CAR).verifyComplete();
    }

    @Test
    void updateSavesWhenCarExists() {
        Car updated = new Car(1, "firstMarca", "newColor");
        when(repository.findById(1)).thenReturn(Mono.just(CAR));
        when(repository.save(updated)).thenReturn(Mono.just(updated));

        StepVerifier.create(service.update(updated)).expectNext(updated).verifyComplete();
    }

    @Test
    void updateFailsWhenCarDoesNotExist() {
        when(repository.findById(1)).thenReturn(Mono.empty());

        StepVerifier.create(service.update(CAR)).expectError(CarNotFoundException.class).verify();
        verify(repository, never()).save(any());
    }

    @Test
    void updateFailsWhenIdIsMissing() {
        StepVerifier.create(service.update(new Car(null, "m", "c")))
                .expectError(IllegalArgumentException.class)
                .verify();
    }

    @Test
    void deleteDelegatesToRepository() {
        when(repository.deleteById(1)).thenReturn(Mono.empty());

        StepVerifier.create(service.delete(1)).verifyComplete();
        verify(repository).deleteById(1);
    }
}
