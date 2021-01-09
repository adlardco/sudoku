package com.adlardco.sudoku.service.grid;

import com.adlardco.sudoku.config.ApplicationConfig;
import com.adlardco.sudoku.service.cell.CellConverter;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

@AllArgsConstructor
public class GridConverter {

    @NonNull private final ApplicationConfig config;

    public Grid fromCellValues(@NonNull List<Integer> cellValues) {
        checkValues(cellValues);

        var numCells = cellValues.size();
        var cellConverter = getCellConverter(numCells);
        var cells = cellValues.stream().map(cellConverter::fromValue).collect(Collectors.toList());
        return new Grid(cells);
    }

    private void checkValues(List<Integer> cellValues) {
        var requiredNumCells = config.getNumCells();
        var numCellValues = cellValues.size();
        checkArgument(numCellValues == requiredNumCells, "Expected %s cell values but found %s", requiredNumCells,
                numCellValues);
    }

    public List<Integer> toCellValues(@NonNull Grid grid) {
        var numCells = grid.getNumCells();
        var cellConverter = getCellConverter(numCells);
        return grid.getCells().stream().map(cellConverter::toValue).collect(Collectors.toList());
    }

    private CellConverter getCellConverter(int numCells) {
        var sizeCalculator = new GridSizeCalculator(numCells);
        var numOptions = sizeCalculator.getSize();
        return new CellConverter(numOptions);
    }
}
