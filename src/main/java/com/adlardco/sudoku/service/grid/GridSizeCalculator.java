package com.adlardco.sudoku.service.grid;

import com.google.common.math.IntMath;
import lombok.Getter;
import lombok.ToString;

import java.math.RoundingMode;

@ToString
@Getter
public class GridSizeCalculator {

    private final int numCells;
    private final int size;
    private final int subSize;

    public GridSizeCalculator(int numCells) {
        var size = squareRoot(numCells);
        var subSize = squareRoot(size);
        this.numCells = numCells;
        this.size = size;
        this.subSize = subSize;
    }

    private static int squareRoot(int value) {
        return IntMath.sqrt(value, RoundingMode.UNNECESSARY);
    }
}
