package com.adlardco.sudoku.service.rule;

import com.adlardco.sudoku.service.cell.Cell;
import com.adlardco.sudoku.service.grid.Grid;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class RuleContextTest {
    @Test
    public void testShouldContinueUntilMaxIterationsReached() {
        var grid = mock(Grid.class);
        when(grid.getCells()).thenReturn(List.of(
            Cell.of(1, 2),
            Cell.of(1, 2)
        ));
        var maxNumIterations = 3;
        var context = new RuleContext(grid, maxNumIterations);
        for(int iteration = 0;iteration < maxNumIterations;iteration++) {
            assertFalse(context.shouldStop());
            context.incrementNumIterations();
        }
        assertTrue(context.shouldStop());
        verify(grid, times(3)).getCells();
    }

    @Test
    public void testShouldStopIfGridSolved() {
        var grid = mock(Grid.class);
        when(grid.getCells()).thenReturn(List.of(
                Cell.of(1, 2),
                Cell.of(1, 2)
        ));
        var context = new RuleContext(grid, 3);
        assertFalse(context.shouldStop());
        when(grid.getCells()).thenReturn(List.of(
                Cell.of(1),
                Cell.of(2)
        ));
        assertTrue(context.shouldStop());
        verify(grid, times(2)).getCells();
    }
}
