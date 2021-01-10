package com.adlardco.sudoku.service.cellsource;

import com.adlardco.sudoku.service.grid.Grid;
import lombok.NonNull;
import static com.google.common.base.Preconditions.checkElementIndex;

public class ColumnCellSource extends AbstractCellSource {

    public ColumnCellSource(@NonNull Grid grid, int index) {
        super(grid, index);
    }

    @Override
    protected boolean match(int otherIndex) {
        checkElementIndex(otherIndex, grid.getNumCells());

        if (index == otherIndex) {
            return false;
        }
        var column = index % grid.getSize();
        var otherColumn = otherIndex % grid.getSize();
        return column == otherColumn;
    }
}