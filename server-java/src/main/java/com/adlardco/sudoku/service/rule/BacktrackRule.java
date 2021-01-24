package com.adlardco.sudoku.service.rule;

import com.adlardco.sudoku.service.option.OptionIterator;
import com.adlardco.sudoku.service.cell.CellState;
import com.adlardco.sudoku.service.cell.CellStore;
import com.adlardco.sudoku.service.grid.Grid;
import lombok.NonNull;

public class BacktrackRule implements Rule {

    private final Grid grid;
    private final OptionIterator iterator;
    private final CellStore cellStore;
    private final RuleProgressMonitor progressMonitor;

    public BacktrackRule(@NonNull Grid grid) {
        this.grid = grid;
        this.iterator = new OptionIterator(grid.getCells());
        this.cellStore = new CellStore();
        this.progressMonitor = new RuleProgressMonitor(grid);
    }

    @Override public void apply() {
        if (anyCellsInvalid()) {
            cellStore.restoreTo(grid.getCells());
        }
        if(progressMonitor.hasNoProgressBeenMade()) {
            cellStore.save(grid.getCells());
            setOption();
        }
    }

    private void setOption() {
        var optionId = iterator.get();
        var cells = grid.getCells();
        var index = optionId.getCellIndex();
        if(index >= cells.size()) {
            return;
        }
        var cell = cells.get(index);
        cell.getOptionSet().set(optionId.getOption());
    }

    private boolean anyCellsInvalid() {
        return grid.getCells().stream().anyMatch(cell -> cell.getState() == CellState.INVALID);
    }
}
