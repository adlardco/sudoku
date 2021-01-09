package com.adlardco.sudoku.service.cell;

import com.adlardco.sudoku.service.option.OptionSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CellTest {

    @Test
    public void testGetState() {
        assertEquals(CellState.INVALID, Cell.of().getState());
        assertEquals(CellState.SOLVED, Cell.of(1).getState());
        assertEquals(CellState.UNSOLVED, Cell.of(1, 2).getState());
    }

    @Test
    public void testGetOptionSet() {
        Assertions.assertEquals(OptionSet.of(), Cell.of().getOptionSet());
        assertEquals(OptionSet.of(1), Cell.of(1).getOptionSet());
        assertEquals(OptionSet.of(1, 2), Cell.of(1, 2).getOptionSet());
    }

    @Test
    public void testToString() {
        assertEquals("[1, 2]", Cell.of(1, 2).toString());
    }
}
