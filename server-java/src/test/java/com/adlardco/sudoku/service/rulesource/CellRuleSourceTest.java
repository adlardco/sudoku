package com.adlardco.sudoku.service.rulesource;

import com.adlardco.sudoku.service.cell.Cell;
import com.adlardco.sudoku.service.grid.Grid;
import com.adlardco.sudoku.service.rule.RemoveDuplicatesRule;
import com.adlardco.sudoku.service.rule.RuleContext;
import com.adlardco.sudoku.service.rule.RuleSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CellRuleSourceTest {
    @Test
    public void testGetRules() {
        var context = mock(RuleContext.class);
        var grid = mock(Grid.class);
        when(grid.getCells()).thenReturn(List.of(Cell.of(1, 2), Cell.of(1, 2)));
        when(grid.getNumCells()).thenReturn(2);
        when(context.getGrid()).thenReturn(grid);
        var ruleSource = new CellRuleSource(context, 0);
        var rules = ruleSource.getRules();
        assertEquals(2, rules.size());
        Assertions.assertEquals(RemoveDuplicatesRule.class, rules.get(0).getClass());
        Assertions.assertEquals(RuleSet.class, rules.get(1).getClass());
        assertEquals(EliminateAllRuleSource.class, ((RuleSet) rules.get(1)).getRuleSource().getClass());
    }
}
