package com.adlardco.sudoku.service.rule;

import com.adlardco.sudoku.service.cellsource.CellSource;
import com.adlardco.sudoku.service.option.OptionSet;
import com.adlardco.sudoku.service.cell.Cell;
import com.adlardco.sudoku.service.cell.CellState;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import java.util.stream.Collectors;

@AllArgsConstructor
public class RemoveDuplicatesRule implements Rule {

    @NonNull
    private final CellSource cellSource;

    @Override
    public void apply() {
        var cell = cellSource.getCell();
        if (cell.getState() != CellState.UNSOLVED) {
            return;
        }
        var options = getOptions();
        cell.getOptionSet().remove(options);
    }

    private OptionSet getOptions() {
        var otherCells = cellSource.getCells();
        var optionsSets = otherCells.stream().filter(cell -> cell.getState() == CellState.SOLVED)
                .map(Cell::getOptionSet).collect(Collectors.toList());
        return OptionSet.of(optionsSets);
    }
}
