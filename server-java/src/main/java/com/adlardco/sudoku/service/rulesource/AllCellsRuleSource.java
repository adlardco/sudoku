package com.adlardco.sudoku.service.rulesource;

import com.adlardco.sudoku.service.rule.Rule;
import com.adlardco.sudoku.service.rule.RuleContext;
import com.adlardco.sudoku.service.rule.RuleSet;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
public class AllCellsRuleSource implements RuleSource {

    @NonNull private final RuleContext context;

    @Override public List<Rule> getRules() {
        return IntStream.range(0, context.getGrid().getNumCells())
                .mapToObj(index -> new RuleSet(context, new CellRuleSource(context, index)))
                .collect(Collectors.toList());
    }
}
