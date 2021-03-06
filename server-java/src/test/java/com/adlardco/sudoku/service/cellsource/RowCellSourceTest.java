package com.adlardco.sudoku.service.cellsource;

import com.adlardco.sudoku.service.cell.Cell;
import com.adlardco.sudoku.service.grid.Grid;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RowCellSourceTest {
    @Test
    public void testGetCells() {
        var grid = mock(Grid.class);
        var cells = IntStream.range(0, 4).mapToObj(Cell::of).collect(Collectors.toList());
        when(grid.getCells()).thenReturn(cells);
        when(grid.getNumCells()).thenReturn(4);
        when(grid.getSize()).thenReturn(2);
        assertEquals(Set.of(cells.get(1)), new RowCellSource(grid, 0).getCells());
        assertEquals(Set.of(cells.get(0)), new RowCellSource(grid, 1).getCells());
        assertEquals(Set.of(cells.get(3)), new RowCellSource(grid, 2).getCells());
        assertEquals(Set.of(cells.get(2)), new RowCellSource(grid, 3).getCells());
    }
}
