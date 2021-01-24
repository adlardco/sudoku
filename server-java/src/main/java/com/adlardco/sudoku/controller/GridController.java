package com.adlardco.sudoku.controller;

import com.adlardco.sudoku.config.ApplicationConfig;
import com.adlardco.sudoku.controller.model.GridModel;
import com.adlardco.sudoku.service.grid.Grid;
import com.adlardco.sudoku.service.grid.GridConverter;
import com.adlardco.sudoku.service.rule.RuleContext;
import com.adlardco.sudoku.service.rule.RuleSet;
import com.adlardco.sudoku.service.rulesource.SolveRuleSource;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class GridController {

    @NonNull private final ApplicationConfig config;
    @NonNull private final GridConverter gridConverter;

    public GridModel solve(@NonNull GridModel gridModel) {
        var grid = gridConverter.fromCellValues(gridModel.getCellValues());
        applyRule(grid);
        return new GridModel(gridConverter.toCellValues(grid));
    }

    private void applyRule(Grid grid) {
        var context = new RuleContext(grid, config.getMaxIterations());
        var rule = new RuleSet(context, new SolveRuleSource(context));
        while (!context.shouldStop()) {
            rule.apply();
        }
    }
}