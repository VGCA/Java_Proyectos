package com.example.demo.controllers;

import com.example.demo.entities.Car;
import com.example.demo.services.CarInterfaceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CarController {

    private CarInterfaceImpl carInterface;

    @PostMapping
    public Car showMeTheCar(@RequestParam String color, @RequestBody Car newCar) {
        return carInterface.showMeTheCar(color,newCar);
    }

    @GetMapping
    public List<Car> showAllCars(){
        return carInterface.theListOfCars();
    }
}
