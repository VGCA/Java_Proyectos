package com.monoflux.crudreactive.domain.model;

/**
 * Entidad de dominio. Es un record inmutable: sustituye a @Data/@AllArgsConstructor/@NoArgsConstructor de Lombok.
 */
public record Car(Integer id, String marca, String color) {

    public Car withId(Integer newId) {
        return new Car(newId, marca, color);
    }
}
