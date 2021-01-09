package com.adlardco.sudoku.service.rulesource;

import com.adlardco.sudoku.service.rule.Rule;
import java.util.List;

public interface RuleSource {

    List<Rule> getRules();
}
