package com.adlardco.sudoku.service.cellsource;

import com.adlardco.sudoku.service.cell.Cell;
import java.util.Set;

public interface CellSource {
    Set<Cell> getCells();

    Cell getCell();
}
