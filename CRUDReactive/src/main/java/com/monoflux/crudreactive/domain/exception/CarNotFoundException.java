package com.monoflux.crudreactive.domain.exception;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(Integer id) {
        super("Car not found with id " + id);
    }
}
