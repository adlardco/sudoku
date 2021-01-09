package com.adlardco.sudoku.service.rule;

import com.adlardco.sudoku.service.cell.Cell;
import com.adlardco.sudoku.service.cellsource.RowCellSource;
import com.adlardco.sudoku.service.grid.Grid;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class RemoveDuplicatesRuleTest {

    @Test
    public void testApply() {
        var grid = mock(Grid.class);
        var cells = List.of(
            Cell.of(1),
            Cell.of(1, 2, 3),
            Cell.of(3),
            Cell.of(4)
        );
        when(grid.getCells()).thenReturn(cells);
        when(grid.getNumCells()).thenReturn(cells.size());
        when(grid.getSize()).thenReturn(cells.size());
        var rule = new RemoveDuplicatesRule(new RowCellSource(grid, 1));
        rule.apply();
        assertEquals(List.of(1, 2, 3, 4).stream().map(Cell::of).collect(Collectors.toList()), grid.getCells());
    }
}
