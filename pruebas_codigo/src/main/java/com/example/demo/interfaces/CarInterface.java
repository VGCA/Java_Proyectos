package com.example.demo.interfaces;

import com.example.demo.entities.Car;

import java.util.List;

public interface CarInterface {
    Car showMeTheCar(String color, Car car);
    List<Car> theListOfCars();
}
