package com.adlardco.sudoku.service.rule;

import com.adlardco.sudoku.service.cell.Cell;
import com.adlardco.sudoku.service.grid.Grid;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RuleProgressMonitorTest {

    @Test
    public void testUpdate() {
        var cells = List.of(Cell.of(1, 2), Cell.of(2, 1));
        var grid = mock(Grid.class);
        when(grid.getCells()).thenReturn(cells);
        var progressMonitor = new RuleProgressMonitor(grid);
        assertFalse(progressMonitor.hasNoProgressBeenMade());
        assertTrue(progressMonitor.hasNoProgressBeenMade());

        cells.get(0).getOptionSet().remove(2);
        assertFalse(progressMonitor.hasNoProgressBeenMade());
        assertTrue(progressMonitor.hasNoProgressBeenMade());
    }
}
