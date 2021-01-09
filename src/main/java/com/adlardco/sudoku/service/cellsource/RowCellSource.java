package com.adlardco.sudoku.service.cellsource;

import com.adlardco.sudoku.service.grid.Grid;
import lombok.NonNull;
import static com.google.common.base.Preconditions.checkElementIndex;

public class RowCellSource extends AbstractCellSource {

    public RowCellSource(@NonNull Grid grid, int index) {
        super(grid, index);
    }

    @Override
    protected boolean match(int otherIndex) {
        checkElementIndex(otherIndex, grid.getNumCells());

        if (index == otherIndex) {
            return false;
        }
        var row = index / grid.getSize();
        var otherRow = otherIndex / grid.getSize();
        return row == otherRow;
    }
}
