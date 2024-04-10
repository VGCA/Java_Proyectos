package com.testing.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DogTest {

    @Mock
    private Dog dog;

    @Test
    void returnDogTest() {
        Dog testingNewDog = new Dog(4, "Wof", "Steve");
        when(dog.createDog(testingNewDog)).thenReturn(testingNewDog);
        assertEquals(testingNewDog, dog.createDog(testingNewDog));
    }
}