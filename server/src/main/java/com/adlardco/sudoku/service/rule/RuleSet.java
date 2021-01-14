package com.adlardco.sudoku.service.rule;

import com.adlardco.sudoku.service.rulesource.RuleSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@AllArgsConstructor
public class RuleSet implements Rule {

    @NonNull private final RuleContext context;
    @Getter @NonNull private final RuleSource ruleSource;

    @Override
    public void apply() {
        var rules = ruleSource.getRules();
        applyRules(rules);
    }

    private void applyRules(List<Rule> rules) {
        rules.forEach(this::applyRule);
    }

    private void applyRule(Rule rule) {
        if (context.shouldStop()) {
            return;
        }
        rule.apply();
        context.incrementNumIterations();
    }
}
