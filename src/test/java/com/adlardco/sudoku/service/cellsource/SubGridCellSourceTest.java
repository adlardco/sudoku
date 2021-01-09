package com.adlardco.sudoku.service.cellsource;

import com.adlardco.sudoku.service.cell.Cell;
import com.adlardco.sudoku.service.grid.Grid;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SubGridCellSourceTest {

    @Test
    public void testGetCells() {
        var grid = mock(Grid.class);
        var cells = IntStream.range(0, 16).mapToObj(Cell::of).collect(Collectors.toList());
        when(grid.getCells()).thenReturn(cells);
        when(grid.getNumCells()).thenReturn(cells.size());
        when(grid.getSize()).thenReturn(4);
        when(grid.getSubSize()).thenReturn(2);

        assertEquals(Stream.of(1, 4, 5).map(cells::get).collect(Collectors.toSet()), new SubGridCellSource(grid, 0).getCells());
        assertEquals(Stream.of(0, 4, 5).map(cells::get).collect(Collectors.toSet()), new SubGridCellSource(grid, 1).getCells());
        assertEquals(Stream.of(0, 1, 5).map(cells::get).collect(Collectors.toSet()), new SubGridCellSource(grid, 4).getCells());
        assertEquals(Stream.of(0, 1, 4).map(cells::get).collect(Collectors.toSet()), new SubGridCellSource(grid, 5).getCells());

        assertEquals(Stream.of(3, 6, 7).map(cells::get).collect(Collectors.toSet()), new SubGridCellSource(grid, 2).getCells());
        assertEquals(Stream.of(2, 6, 7).map(cells::get).collect(Collectors.toSet()), new SubGridCellSource(grid, 3).getCells());
        assertEquals(Stream.of(2, 3, 7).map(cells::get).collect(Collectors.toSet()), new SubGridCellSource(grid, 6).getCells());
        assertEquals(Stream.of(2, 3, 6).map(cells::get).collect(Collectors.toSet()), new SubGridCellSource(grid, 7).getCells());

        assertEquals(Stream.of(9, 12, 13).map(cells::get).collect(Collectors.toSet()), new SubGridCellSource(grid, 8).getCells());
        assertEquals(Stream.of(8, 12, 13).map(cells::get).collect(Collectors.toSet()), new SubGridCellSource(grid, 9).getCells());
        assertEquals(Stream.of(8, 9, 13).map(cells::get).collect(Collectors.toSet()), new SubGridCellSource(grid, 12).getCells());
        assertEquals(Stream.of(8, 9, 12).map(cells::get).collect(Collectors.toSet()), new SubGridCellSource(grid, 13).getCells());

        assertEquals(Stream.of(11, 14, 15).map(cells::get).collect(Collectors.toSet()), new SubGridCellSource(grid, 10).getCells());
        assertEquals(Stream.of(10, 14, 15).map(cells::get).collect(Collectors.toSet()), new SubGridCellSource(grid, 11).getCells());
        assertEquals(Stream.of(10, 11, 15).map(cells::get).collect(Collectors.toSet()), new SubGridCellSource(grid, 14).getCells());
        assertEquals(Stream.of(10, 11, 14).map(cells::get).collect(Collectors.toSet()), new SubGridCellSource(grid, 15).getCells());
    }
}
