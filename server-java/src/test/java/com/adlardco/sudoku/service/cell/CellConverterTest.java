package com.adlardco.sudoku.service.cell;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CellConverterTest {

    private CellConverter converter;

    @BeforeEach
    public void setup() {
        converter = new CellConverter(2);
    }

    @Test
    public void testFromValue() {
        assertEquals(Cell.of(1, 2), converter.fromValue(0));
        assertEquals(Cell.of(1), converter.fromValue(1));
        assertEquals(Cell.of(2), converter.fromValue(2));
        assertEquals(Cell.of(1, 2), converter.fromValue(3));
    }

    @Test
    public void testToValue() {
        assertEquals(0, converter.toValue(Cell.of()));
        assertEquals(1, converter.toValue(Cell.of(1)));
        assertEquals(0, converter.toValue(Cell.of(1, 2)));
        assertEquals(0, converter.toValue(Cell.of(3)));
    }
}