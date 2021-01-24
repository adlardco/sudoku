package com.adlardco.sudoku.service.rulesource;

import com.adlardco.sudoku.service.grid.Grid;
import com.adlardco.sudoku.service.rule.BacktrackRule;
import com.adlardco.sudoku.service.rule.RuleContext;
import com.adlardco.sudoku.service.rule.RuleSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SolveRuleSourceTest {
    @Test
    public void testGetRules() {
        var context = mock(RuleContext.class);
        var grid = mock(Grid.class);
        when(grid.getNumCells()).thenReturn(2);
        when(context.getGrid()).thenReturn(grid);
        var ruleSource = new SolveRuleSource(context);
        var rules = ruleSource.getRules();
        assertEquals(2, rules.size());
        Assertions.assertEquals(BacktrackRule.class, rules.get(0).getClass());
        var ruleSet = rules.get(1);
        Assertions.assertEquals(RuleSet.class, ruleSet.getClass());
        Assertions.assertEquals(AllCellsRuleSource.class, ((RuleSet) ruleSet).getRuleSource().getClass());
    }
}
