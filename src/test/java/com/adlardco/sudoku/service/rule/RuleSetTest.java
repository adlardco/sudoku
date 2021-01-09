package com.adlardco.sudoku.service.rule;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class RuleSetTest {

    @Test
    public void testApplyAppliesAllRulesWhenShouldContinue() {
        var context = mock(RuleContext.class);
        when(context.shouldStop()).thenReturn(false);
        var rules = List.of(mock(Rule.class), mock(Rule.class));
        var rule = new RuleSet(context, () -> rules);
        rule.apply();
        rules.forEach(testRule -> verify(testRule, times(1)).apply());
        verifyNoMoreInteractions(rules.toArray(new Object[0]));
    }

    @Test
    public void testApplyAppliesNoRulesWhenShouldStop() {
        var context = mock(RuleContext.class);
        when(context.shouldStop()).thenReturn(true);
        var rules = List.of(mock(Rule.class), mock(Rule.class));
        var rule = new RuleSet(context, () -> rules);
        rule.apply();
        rules.forEach(testRule -> verify(testRule, times(0)).apply());
        verifyNoMoreInteractions(rules.toArray(new Object[0]));
    }
}
