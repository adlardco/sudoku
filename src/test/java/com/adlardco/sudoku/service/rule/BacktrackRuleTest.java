package com.adlardco.sudoku.service.rule;

import com.adlardco.sudoku.service.cell.Cell;
import com.adlardco.sudoku.service.grid.Grid;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BacktrackRuleTest {

    @Test
    public void testApply() {
        var cells = new ArrayList<>(List.of(
            Cell.of(1, 4),
            Cell.of(1, 2),
            Cell.of(1, 3),
            Cell.of(1, 2)
        ));
        var grid = mock(Grid.class);
        when(grid.getCells()).thenReturn(cells);
        var rule = new BacktrackRule(grid);
        rule.apply();

        // apply again with no changes. i.e. no progress
        rule.apply();

        // check it applies the first option
        assertEquals(List.of(
           Cell.of(1),
           Cell.of(1, 2),
           Cell.of(1, 3),
           Cell.of(1, 2)
        ), cells);

        // and check it clears the option if one of the cells is invalid and selects the next possible
        cells.get(1).getOptionSet().clear();
        rule.apply();
        assertEquals(List.of(
                Cell.of(4),
                Cell.of(1, 2),
                Cell.of(1, 3),
                Cell.of(1, 2)
        ), cells);
    }
}
