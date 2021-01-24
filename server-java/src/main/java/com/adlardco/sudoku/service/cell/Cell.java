package com.adlardco.sudoku.service.cell;

import com.adlardco.sudoku.service.option.OptionSet;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@EqualsAndHashCode
public class Cell {
    @Getter private final OptionSet optionSet;

    public Cell(@NonNull Cell cell) {
        this.optionSet = new OptionSet(cell.getOptionSet());
    }

    private Cell(@NonNull OptionSet optionSet) {
        this.optionSet = optionSet;
    }

    public static Cell of(int... options) {
        var optionSet = OptionSet.of(options);
        return new Cell(optionSet);
    }

    public CellState getState() {
        var numOptions = optionSet.getSize();
        return switch (numOptions) {
            case 0 -> CellState.INVALID;
            case 1 -> CellState.SOLVED;
            default -> CellState.UNSOLVED;
        };
    }

    @Override public String toString() {
        return optionSet.toString();
    }
}
