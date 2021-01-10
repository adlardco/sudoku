package com.adlardco.sudoku.service.option;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionSetFactoryTest {
    @Test
    public void testGetAll() {
        assertEquals(OptionSet.of(1, 2), new OptionSetFactory(2).get());
        assertEquals(OptionSet.of(1, 2, 3), new OptionSetFactory(3).get());
    }
}
