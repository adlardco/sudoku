package com.adlardco.sudoku.service.rulesource;

import com.adlardco.sudoku.service.cellsource.CellSource;
import com.adlardco.sudoku.service.cellsource.ColumnCellSource;
import com.adlardco.sudoku.service.cellsource.RowCellSource;
import com.adlardco.sudoku.service.cellsource.SubGridCellSource;
import com.adlardco.sudoku.service.grid.Grid;
import com.adlardco.sudoku.service.option.OptionSetFactory;
import com.adlardco.sudoku.service.rule.EliminateRule;
import com.adlardco.sudoku.service.rule.Rule;
import com.adlardco.sudoku.service.rule.RuleContext;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
public final class EliminateAllRuleSource implements RuleSource {

    @NonNull private final RuleContext context;
    private final int index;

    @Override
    public List<Rule> getRules() {
        var grid = context.getGrid();
        var gridSize = grid.getSize();
        var optionSetFactory = new OptionSetFactory(gridSize);
        var cellSources = getCellSources(grid);
        return cellSources.map(source -> new EliminateRule(optionSetFactory, source)).collect(Collectors.toList());
    }

    private Stream<CellSource> getCellSources(Grid grid) {
        return Stream.of(new RowCellSource(grid, index), new ColumnCellSource(grid, index),
                new SubGridCellSource(grid, index));
    }
}
