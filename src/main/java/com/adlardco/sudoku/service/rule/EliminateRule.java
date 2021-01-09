package com.adlardco.sudoku.service.rule;

import com.adlardco.sudoku.service.cellsource.CellSource;
import com.adlardco.sudoku.service.option.OptionSet;
import com.adlardco.sudoku.service.option.OptionSetFactory;
import com.adlardco.sudoku.service.cell.Cell;
import com.adlardco.sudoku.service.cell.CellState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.stream.Collectors;

@AllArgsConstructor
public class EliminateRule implements Rule {

    @NonNull
    private final OptionSetFactory optionSetFactory;
    @Getter
    @NonNull
    private final CellSource cellSource;

    @Override
    public void apply() {
        var cell = cellSource.getCell();
        if (cell.getState() != CellState.UNSOLVED) {
            return;
        }
        var options = getOptions();
        var allOptions = optionSetFactory.get();
        allOptions.remove(options);
        if (!allOptions.isEmpty()) {
            cell.getOptionSet().retain(allOptions);
        }
    }

    private OptionSet getOptions() {
        var optionSets = cellSource.getCells().stream().map(Cell::getOptionSet).collect(Collectors.toList());
        return OptionSet.of(optionSets);
    }
}
