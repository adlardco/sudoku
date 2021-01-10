package com.adlardco.sudoku.service.rulesource;

import com.adlardco.sudoku.service.grid.Grid;
import com.adlardco.sudoku.service.rule.RuleContext;
import com.adlardco.sudoku.service.rule.RuleSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.stream.IntStream;

public class AllCellsRuleSourceTest {

    @Test
    public void testGetRules() {
        var context = mock(RuleContext.class);
        var grid = mock(Grid.class);
        when(grid.getNumCells()).thenReturn(2);
        when(context.getGrid()).thenReturn(grid);
        var ruleSource = new AllCellsRuleSource(context);
        var rules = ruleSource.getRules();

        assertEquals(2, rules.size());
        IntStream.range(0, 2).forEach(index -> {
            var rule = rules.get(index);
            Assertions.assertEquals(RuleSet.class, rule.getClass());
            Assertions.assertEquals(CellRuleSource.class, ((RuleSet) rule).getRuleSource().getClass());
        });
    }
}
