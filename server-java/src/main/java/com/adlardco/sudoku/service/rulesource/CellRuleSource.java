package com.adlardco.sudoku.service.rulesource;

import com.adlardco.sudoku.service.cellsource.RowColumnSubGridCellSource;
import com.adlardco.sudoku.service.rule.RemoveDuplicatesRule;
import com.adlardco.sudoku.service.rule.Rule;
import com.adlardco.sudoku.service.rule.RuleContext;
import com.adlardco.sudoku.service.rule.RuleSet;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;

@AllArgsConstructor
public final class CellRuleSource implements RuleSource {

    @NonNull private final RuleContext context;
    private final int index;

    @Override
    public List<Rule> getRules() {
        return List.of(new RemoveDuplicatesRule(new RowColumnSubGridCellSource(context.getGrid(), index)),
                new RuleSet(context, new EliminateAllRuleSource(context, index)));
    }
}
