package com.adlardco.sudoku.service.rule;

import com.adlardco.sudoku.service.cell.CellState;
import com.adlardco.sudoku.service.grid.Grid;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RuleContext {

    @Getter @NonNull private final Grid grid;
    private final int maxNumIterations;
    private int numIterations = 0;

    public boolean shouldStop() {
        return maxNumIterationsReached() || allCellsSolved();
    }

    public void incrementNumIterations() {
        numIterations++;
    }

    private boolean allCellsSolved() {
        return grid.getCells().stream().allMatch(cell -> cell.getState() == CellState.SOLVED);
    }

    private boolean maxNumIterationsReached() {
        return numIterations >= maxNumIterations;
    }
}
