package com.adlardco.sudoku.service.cell;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CellStore {

    @Getter
    private final List<Cell> cells = new ArrayList<>();

    public void save(@NonNull List<Cell> cells) {
        var copiedCells = copyCells(cells);
        setCells(copiedCells, this.cells);
    }

    public void restoreTo(@NonNull List<Cell> cells) {
        if (this.cells.isEmpty()) {
            return;
        }
        setCells(this.cells, cells);
        this.cells.clear();
    }

    private static List<Cell> copyCells(List<Cell> cells) {
        return cells.stream().map(Cell::new).collect(Collectors.toList());
    }

    private static void setCells(List<Cell> sourceCells, List<Cell> targetCells) {
        targetCells.clear();
        targetCells.addAll(sourceCells);
    }
}
