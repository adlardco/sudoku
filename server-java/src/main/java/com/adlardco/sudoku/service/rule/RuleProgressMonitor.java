package com.adlardco.sudoku.service.rule;

import com.adlardco.sudoku.service.cell.CellState;
import com.adlardco.sudoku.service.grid.Grid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RuleProgressMonitor {

    @NonNull private final Grid grid;
    private Long numCellsSolved = null;

    public boolean hasNoProgressBeenMade() {
        var lastNumCellsSolved = numCellsSolved;
        updateNumCellsSolved();
        return lastNumCellsSolved != null && numCellsSolved <= lastNumCellsSolved;
    }

    private void updateNumCellsSolved() {
        numCellsSolved = grid.getCells().stream().filter(cell -> cell.getState() == CellState.SOLVED).count();
    }
}
