package com.adlardco.sudoku.service.cellsource;

import com.adlardco.sudoku.service.cell.Cell;
import com.adlardco.sudoku.service.grid.Grid;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
public abstract class AbstractCellSource implements CellSource {

    @NonNull protected final Grid grid;
    protected final int index;

    @Override public final Set<Cell> getCells() {
        var numCells = grid.getNumCells();
        return IntStream.range(0, numCells).filter(this::match).mapToObj(this::getCellAtIndex)
                .collect(Collectors.toSet());
    }

    protected abstract boolean match(int otherIndex);

    @Override public final Cell getCell() {
        return getCellAtIndex(index);
    }

    private Cell getCellAtIndex(int index) {
        return grid.getCells().get(index);
    }
}
