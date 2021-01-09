package com.adlardco.sudoku.service.cellsource;

import com.adlardco.sudoku.service.cell.Cell;
import com.adlardco.sudoku.service.grid.Grid;
import lombok.NonNull;
import java.util.Set;

public class RowColumnSubGridCellSource implements CellSource {

    private final CellSource cellSource;

    public RowColumnSubGridCellSource(@NonNull Grid grid, int index) {
        this.cellSource = new CellSourceSet(
            new RowCellSource(grid, index),
            new ColumnCellSource(grid, index),
            new SubGridCellSource(grid, index)
        );
    }

    @Override
    public Set<Cell> getCells() {
        return cellSource.getCells();
    }

    @Override
    public Cell getCell() {
        return cellSource.getCell();
    }
}
