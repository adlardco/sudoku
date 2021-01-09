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

public class RowColumnSubGridCellSourceTest {
    @Test
    public void testGetCells() {
        var grid = mock(Grid.class);
        var cells = IntStream.range(0, 16).mapToObj(Cell::of).collect(Collectors.toList());
        when(grid.getCells()).thenReturn(cells);
        when(grid.getNumCells()).thenReturn(cells.size());
        when(grid.getSize()).thenReturn(4);
        when(grid.getSubSize()).thenReturn(2);

        assertEquals(Stream.of(1, 4, 5, 2, 3, 8, 12).map(cells::get).collect(Collectors.toSet()), new RowColumnSubGridCellSource(grid, 0).getCells());
        assertEquals(Stream.of(0, 4, 5, 2, 3, 9, 13).map(cells::get).collect(Collectors.toSet()), new RowColumnSubGridCellSource(grid, 1).getCells());
        assertEquals(Stream.of(0, 1, 5, 6, 7, 8, 12).map(cells::get).collect(Collectors.toSet()), new RowColumnSubGridCellSource(grid, 4).getCells());
        assertEquals(Stream.of(0, 1, 4, 6, 7, 9, 13).map(cells::get).collect(Collectors.toSet()), new RowColumnSubGridCellSource(grid, 5).getCells());

        assertEquals(Stream.of(3, 6, 7, 0, 1, 10, 14).map(cells::get).collect(Collectors.toSet()), new RowColumnSubGridCellSource(grid, 2).getCells());
        assertEquals(Stream.of(2, 6, 7, 0, 1, 11, 15).map(cells::get).collect(Collectors.toSet()), new RowColumnSubGridCellSource(grid, 3).getCells());
        assertEquals(Stream.of(2, 3, 7, 4, 5, 10, 14).map(cells::get).collect(Collectors.toSet()), new RowColumnSubGridCellSource(grid, 6).getCells());
        assertEquals(Stream.of(2, 3, 6, 4, 5, 11, 15).map(cells::get).collect(Collectors.toSet()), new RowColumnSubGridCellSource(grid, 7).getCells());

        assertEquals(Stream.of(9, 12, 13, 10, 11, 0, 4).map(cells::get).collect(Collectors.toSet()), new RowColumnSubGridCellSource(grid, 8).getCells());
        assertEquals(Stream.of(8, 12, 13, 10, 11, 1, 5).map(cells::get).collect(Collectors.toSet()), new RowColumnSubGridCellSource(grid, 9).getCells());
        assertEquals(Stream.of(8, 9, 13, 14, 15, 0, 4).map(cells::get).collect(Collectors.toSet()), new RowColumnSubGridCellSource(grid, 12).getCells());
        assertEquals(Stream.of(8, 9, 12, 14, 15, 1, 5).map(cells::get).collect(Collectors.toSet()), new RowColumnSubGridCellSource(grid, 13).getCells());

        assertEquals(Stream.of(11, 14, 15, 8, 9, 2, 6).map(cells::get).collect(Collectors.toSet()), new RowColumnSubGridCellSource(grid, 10).getCells());
        assertEquals(Stream.of(10, 14, 15, 8, 9, 3, 7).map(cells::get).collect(Collectors.toSet()), new RowColumnSubGridCellSource(grid, 11).getCells());
        assertEquals(Stream.of(10, 11, 15, 12, 13, 2, 6).map(cells::get).collect(Collectors.toSet()), new RowColumnSubGridCellSource(grid, 14).getCells());
        assertEquals(Stream.of(10, 11, 14, 12, 13, 3, 7).map(cells::get).collect(Collectors.toSet()), new RowColumnSubGridCellSource(grid, 15).getCells());
    }
}
