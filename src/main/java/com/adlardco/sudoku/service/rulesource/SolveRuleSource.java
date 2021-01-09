package com.adlardco.sudoku.service.rulesource;

import com.adlardco.sudoku.service.rule.BacktrackRule;
import com.adlardco.sudoku.service.rule.Rule;
import com.adlardco.sudoku.service.rule.RuleContext;
import com.adlardco.sudoku.service.rule.RuleSet;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

public class SolveRuleSource implements RuleSource {

    @Getter @NonNull private final List<Rule> rules;

    public SolveRuleSource(@NonNull RuleContext context) {
        this.rules = List.of(
            new BacktrackRule(context.getGrid()),
            new RuleSet(context, new AllCellsRuleSource(context))
        );
    }
}
