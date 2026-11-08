package com.monoflux.crudreactive.infrastructure.adapter.in.web;

import com.monoflux.crudreactive.domain.model.Car;

/**
 * Contrato HTTP: desacopla la API REST del modelo de dominio.
 */
public record CarDto(Integer id, String marca, String color) {

    public static CarDto fromDomain(Car car) {
        return new CarDto(car.id(), car.marca(), car.color());
    }

    public Car toDomain() {
        return new Car(id, marca, color);
    }
}
