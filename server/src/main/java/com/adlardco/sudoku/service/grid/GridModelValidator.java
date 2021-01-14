package com.adlardco.sudoku.service.grid;

import com.adlardco.sudoku.config.ApplicationConfig;
import com.adlardco.sudoku.controller.model.GridModel;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;

@AllArgsConstructor
public class GridModelValidator {

    @NonNull private final ApplicationConfig config;

    public boolean isValid(GridModel grid) {
        var cellValues = grid == null ? null : grid.getCellValues();
        return hasValidNumberOfValues(cellValues) && noInvalidCells(cellValues);
    }

    private boolean hasValidNumberOfValues(List<Integer> values) {
        return values != null && values.size() == config.getNumCells();
    }

    private boolean noInvalidCells(List<Integer> values) {
        var sizeCalculator = new GridSizeCalculator(values.size());
        var numOptions = sizeCalculator.getSize();
        return values.stream().noneMatch(value -> value < 0 || value > numOptions);
    }
}
