package com.adlardco.sudoku.service.rule;

import com.adlardco.sudoku.service.cell.Cell;
import com.adlardco.sudoku.service.cellsource.RowCellSource;
import com.adlardco.sudoku.service.grid.Grid;
import com.adlardco.sudoku.service.option.OptionSetFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class EliminateRuleTest {

    @Test
    public void testApply() {
        var cells = List.of(
            Cell.of(1, 2, 3),
            Cell.of(1, 2, 3, 4),
            Cell.of(1, 2, 3),
            Cell.of(1, 2, 3)
        );
        var grid = mock(Grid.class);
        when(grid.getCells()).thenReturn(cells);
        when(grid.getNumCells()).thenReturn(cells.size());
        when(grid.getSize()).thenReturn(cells.size());
        var optionSetFactory = new OptionSetFactory(grid.getSize());
        var rule = new EliminateRule(optionSetFactory, new RowCellSource(grid, 1));
        rule.apply();
        assertEquals(List.of(
                Cell.of(1, 2, 3),
                Cell.of(4),
                Cell.of(1, 2, 3),
                Cell.of(1, 2, 3)
            ), grid.getCells()
        );
    }
}
