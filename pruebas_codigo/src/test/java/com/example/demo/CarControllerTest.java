package com.example.demo;

import com.example.demo.controllers.CarController;
import com.example.demo.entities.Car;
import com.example.demo.services.CarInterfaceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarInterfaceImpl carInterface;

    @Autowired
    private ObjectMapper objectMapper;

    private Car sampleCar;

    @BeforeEach
    void setUp() {
        sampleCar = new Car();
        sampleCar.setId(1);
        sampleCar.setColor("red");
        sampleCar.setModel("Toyota");
    }


    @Test
    void showMeTheCar_shouldReturn200WithCar_whenValidInput() throws Exception {
        when(carInterface.showMeTheCar(eq("red"), any(Car.class)))
                .thenReturn(sampleCar);

        mockMvc.perform(post("/")
                        .param("color", "red")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleCar)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.color").value("red"))
                .andExpect(jsonPath("$.model").value("Toyota"));

        verify(carInterface, times(1)).showMeTheCar(eq("red"), any(Car.class));
    }

    @Test
    void showMeTheCar_shouldReturn400_whenColorParamIsMissing() throws Exception {
        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleCar)))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(carInterface);
    }

    @Test
    void showMeTheCar_shouldReturn400_whenBodyIsMissing() throws Exception {
        mockMvc.perform(post("/")
                        .param("color", "red")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(carInterface);
    }

    @Test
    void showMeTheCar_shouldPassCorrectColorToService() throws Exception {
        when(carInterface.showMeTheCar(eq("blue"), any(Car.class)))
                .thenReturn(sampleCar);

        mockMvc.perform(post("/")
                        .param("color", "blue")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleCar)))
                .andExpect(status().isOk());

        verify(carInterface).showMeTheCar(eq("blue"), any(Car.class));
    }

    @Test
    void showMeTheCar_shouldReturnNull_whenServiceReturnsNull() throws Exception {
        when(carInterface.showMeTheCar(anyString(), any(Car.class)))
                .thenReturn(null);

        mockMvc.perform(post("/")
                        .param("color", "red")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleCar)))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }


    @Test
    void showAllCars_shouldReturn200WithList_whenCarsExist() throws Exception {
        Car car2 = new Car();
        car2.setId(2);
        car2.setColor("blue");
        car2.setModel("Honda");

        List<Car> cars = Arrays.asList(sampleCar, car2);
        when(carInterface.theListOfCars()).thenReturn(cars);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].color").value("red"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].color").value("blue"));

        verify(carInterface, times(1)).theListOfCars();
    }

    @Test
    void showAllCars_shouldReturnEmptyList_whenNoCarsExist() throws Exception {
        when(carInterface.theListOfCars()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
    

    @Test
    void showAllCars_shouldReturnJsonContentType() throws Exception {
        when(carInterface.theListOfCars()).thenReturn(List.of(sampleCar));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void showAllCars_shouldCallServiceExactlyOnce() throws Exception {
        when(carInterface.theListOfCars()).thenReturn(List.of(sampleCar));

        mockMvc.perform(get("/"));

        verify(carInterface, times(1)).theListOfCars();
        verifyNoMoreInteractions(carInterface);
    }
}