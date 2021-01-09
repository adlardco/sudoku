package com.adlardco.sudoku.service.cellsource;

import com.adlardco.sudoku.service.grid.Grid;
import lombok.NonNull;
import static com.google.common.base.Preconditions.checkElementIndex;

public class SubGridCellSource extends AbstractCellSource {

    public SubGridCellSource(@NonNull Grid grid, int index) {
        super(grid, index);
    }

    @Override
    protected boolean match(int otherIndex) {
        checkElementIndex(otherIndex, grid.getNumCells());

        if (index == otherIndex) {
            return false;
        }
        var row = index / grid.getSize();
        var column = index % grid.getSize();
        var otherRow = otherIndex / grid.getSize();
        var otherColumn = otherIndex % grid.getSize();
        return subGridMatch(row, otherRow) && subGridMatch(column, otherColumn);
    }

    private boolean subGridMatch(int rowOrColumn, int otherRowOrColumn) {
        var subGridRowOrColumn = rowOrColumn / grid.getSubSize();
        var otherSubGridRowOrColumn = otherRowOrColumn / grid.getSubSize();
        return subGridRowOrColumn == otherSubGridRowOrColumn;
    }
}
