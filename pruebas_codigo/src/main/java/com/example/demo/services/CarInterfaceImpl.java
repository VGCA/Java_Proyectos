package com.example.demo.services;

import com.example.demo.entities.Car;
import com.example.demo.interfaces.CarInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarInterfaceImpl implements CarInterface {

    private static List<Car> listCars = new ArrayList<>();

    @Override
    public Car showMeTheCar(String color, Car car) {
        Car newCar = car;
        newCar.setColor(color);
        listCars.add(newCar);
        return newCar;
    }

    @Override
    public List<Car> theListOfCars() {
        return listCars;
    }
}
