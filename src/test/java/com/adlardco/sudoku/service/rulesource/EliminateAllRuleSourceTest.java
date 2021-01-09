package com.adlardco.sudoku.service.rulesource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.IntStream;

import com.adlardco.sudoku.service.cellsource.ColumnCellSource;
import com.adlardco.sudoku.service.cellsource.RowCellSource;
import com.adlardco.sudoku.service.cellsource.SubGridCellSource;
import com.adlardco.sudoku.service.grid.Grid;
import com.adlardco.sudoku.service.rule.EliminateRule;
import com.adlardco.sudoku.service.rule.RuleContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EliminateAllRuleSourceTest {
    @Test
    public void testGetRules() {
        var context = mock(RuleContext.class);
        var grid = mock(Grid.class);
        when(grid.getNumCells()).thenReturn(2);
        when(context.getGrid()).thenReturn(grid);
        var ruleSource = new EliminateAllRuleSource(context, 0);
        var rules = ruleSource.getRules();
        assertEquals(3, rules.size());
        var expectedCellSourceClasses = List.of(RowCellSource.class, ColumnCellSource.class, SubGridCellSource.class);
        IntStream.range(0, 3).forEach(index -> {
            var rule = rules.get(index);
            Assertions.assertEquals(EliminateRule.class, rule.getClass());
            Assertions.assertEquals(expectedCellSourceClasses.get(index), ((EliminateRule) rule).getCellSource().getClass());
        });
    }
}
