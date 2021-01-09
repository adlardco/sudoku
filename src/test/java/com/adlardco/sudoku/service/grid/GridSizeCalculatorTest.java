package com.adlardco.sudoku.service.grid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GridSizeCalculatorTest {

    private GridSizeCalculator calculator;

    @BeforeEach
    public void setup() {
        calculator = new GridSizeCalculator(16);
    }

    @Test
    public void testGetNumCells() {
        assertEquals(16, calculator.getNumCells());
    }

    @Test
    public void testGetSize() {
        assertEquals(4, calculator.getSize());
    }

    @Test
    public void testGetSubSize() {
        assertEquals(2, calculator.getSubSize());
    }

    @Test
    public void testToString() {
        assertEquals("GridSizeCalculator(numCells=16, size=4, subSize=2)", calculator.toString());
    }
}
