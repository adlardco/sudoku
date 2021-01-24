package com.adlardco.sudoku.service.grid;

import com.adlardco.sudoku.config.ApplicationConfig;
import com.adlardco.sudoku.service.cell.Cell;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GridConverterTest {

    @Test
    public void testConvert() {
        var config = mock(ApplicationConfig.class);
        when(config.getNumCells()).thenReturn(16);

        var converter = new GridConverter(config);
        var cellValues = List.of(
            1, 2, 3, 4,
            3, 4, 0, 2,
            2, 0, 4, 3,
            4, 3, 2, 1
        );
        var grid = converter.fromCellValues(cellValues);
        assertEquals(new Grid(
            Cell.of(1), Cell.of(2), Cell.of(3), Cell.of(4),
            Cell.of(3), Cell.of(4), Cell.of(1, 2, 3, 4), Cell.of(2),
            Cell.of(2), Cell.of(1, 2, 3, 4), Cell.of(4), Cell.of(3),
            Cell.of(4), Cell.of(3), Cell.of(2), Cell.of(1)
        ), grid);
        assertEquals(cellValues, converter.toCellValues(grid));
    }
}
