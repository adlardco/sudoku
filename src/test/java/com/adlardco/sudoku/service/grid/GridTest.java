package com.adlardco.sudoku.service.grid;

import com.adlardco.sudoku.service.cell.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GridTest {

    private Grid grid;

    @BeforeEach
    public void setup() {
        var cells = IntStream.range(0, 16).mapToObj(Cell::of).collect(Collectors.toList());
        grid = new Grid(cells);
    }

    @Test
    public void testGetNumCells() {
        assertEquals(16, grid.getNumCells());
    }

    @Test
    public void testGetSize() {
        assertEquals(4, grid.getSize());
    }

    @Test
    public void testGetSubSize() {
        assertEquals(2, grid.getSubSize());
    }

    @Test
    public void testToString() {
        assertEquals("[[0], [1], [2], [3], [4], [5], [6], [7], [8], [9], [10], [11], [12], [13], [14], [15]]",
                grid.toString());
    }
}
