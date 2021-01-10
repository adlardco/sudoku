package com.adlardco.sudoku.service.grid;

import com.adlardco.sudoku.config.ApplicationConfig;
import com.adlardco.sudoku.controller.model.GridModel;
import com.adlardco.sudoku.controller.model.GridModelValid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@AllArgsConstructor
public class GridModelValidator implements ConstraintValidator<GridModelValid, GridModel> {

    @NonNull private final ApplicationConfig config;

    @Override
    public boolean isValid(GridModel gridModel, ConstraintValidatorContext context) {
        var cellValues = gridModel.getCellValues();
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
