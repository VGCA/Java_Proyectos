package com.testing.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SimpleCalculatorTest {

    @Mock
    private SimpleCalculator simpleCalculator;

    @Test
    void add() {
        when(simpleCalculator.add(2, 3)).thenReturn(5);
        assertEquals(5, simpleCalculator.add(2, 3));
    }

    @Test
    void quit() {
        when(simpleCalculator.quit(25, 5)).thenReturn(20);
        assertEquals(20, simpleCalculator.quit(25, 5));
    }
}