package com.adlardco.sudoku.service.grid;

import com.adlardco.sudoku.service.cell.Cell;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@EqualsAndHashCode(exclude = "sizeCalculator")
public class Grid {
    @Getter private final List<Cell> cells;
    private final GridSizeCalculator sizeCalculator;

    public Grid(@NonNull List<Cell> cells) {
        this.cells = cells;
        this.sizeCalculator = new GridSizeCalculator(cells.size());
    }

    public Grid(Cell... cells) {
        this(Arrays.asList(cells));
    }

    public Grid(int numCells) {
        this(IntStream.range(0, numCells).mapToObj(i -> Cell.of()).collect(Collectors.toList()));
    }

    public int getNumCells() {
        return sizeCalculator.getNumCells();
    }

    public int getSize() {
        return sizeCalculator.getSize();
    }

    public int getSubSize() {
        return sizeCalculator.getSubSize();
    }

    @Override public String toString() {
        return cells.toString();
    }
}
