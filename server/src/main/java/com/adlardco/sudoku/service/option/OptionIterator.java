package com.adlardco.sudoku.service.option;

import com.adlardco.sudoku.service.cell.Cell;
import com.adlardco.sudoku.service.cell.CellState;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OptionIterator {

    private final List<Cell> cells;
    private OptionId optionId;

    public OptionIterator(@NonNull List<Cell> cells) {
        this(cells, new OptionId(0, 0));
    }

    public OptionId get() {
        var nextOption = getNextOption(optionId.getCellIndex(), optionId.getOption());
        if (nextOption > 0) {
            optionId = new OptionId(optionId.getCellIndex(), nextOption);
        } else {
            nextCell();
        }
        return optionId;
    }

    private void nextCell() {
        var optionIdCellIndex = optionId.getCellIndex();
        var cellIndex = optionIdCellIndex;
        do {
            cellIndex = cellIndex >= cells.size() - 1 ? 0 : cellIndex + 1;
        } while (cellIndex != optionIdCellIndex && cells.get(cellIndex).getState() != CellState.UNSOLVED);
        var firstOption = getNextOption(cellIndex, 0);
        optionId = new OptionId(cellIndex, firstOption);
    }

    private int getNextOption(int cellIndex, int option) {
        var cell = cells.get(cellIndex);
        return cell.getOptionSet().getNextHighest(option).orElse(0);
    }
}
