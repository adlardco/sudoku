package com.adlardco.sudoku.controller;

import com.adlardco.sudoku.config.ApplicationConfig;
import com.adlardco.sudoku.controller.model.GridModel;
import com.adlardco.sudoku.service.grid.GridConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GridControllerTest {

    private GridController controller;

    @BeforeEach
    public void setup() {
        var config = mock(ApplicationConfig.class);
        when(config.getNumCells()).thenReturn(81);
        when(config.getMaxIterations()).thenReturn(15000);

        var converter = new GridConverter(config);
        controller = new GridController(config, converter);
    }

    @Test
    public void testSolveInvalidGridSizeThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            var values = List.of(16);
            controller.solve(new GridModel(values));
        });
    }

    @Test
    public void testSolve() {
        Assertions.assertEquals(new GridModel(List.of(
            9, 8, 3, 4, 2, 1, 6, 7, 5,
            2, 6, 1, 5, 9, 7, 3, 4, 8,
            7, 5, 4, 6, 8, 3, 1, 9, 2,
            3, 4, 2, 9, 5, 6, 8, 1, 7,
            8, 1, 6, 7, 4, 2, 5, 3, 9,
            5, 9, 7, 1, 3, 8, 2, 6, 4,
            1, 2, 5, 3, 7, 9, 4, 8, 6,
            6, 7, 8, 2, 1, 4, 9, 5, 3,
            4, 3, 9, 8, 6, 5, 7, 2, 1
        )), controller.solve(new GridModel(List.of(
            9, 0, 0, 0, 2, 0, 6, 0, 0,
            0, 6, 0, 5, 9, 0, 3, 4, 0,
            0, 0, 0, 0, 0, 3, 0, 0, 0,
            3, 0, 2, 0, 0, 0, 0, 0, 7,
            8, 0, 0, 0, 0, 0, 0, 0, 9,
            5, 0, 0, 0, 0, 0, 2, 0, 4,
            0, 0, 0, 3, 0, 0, 0, 0, 0,
            0, 7, 8, 0, 1, 4, 0, 5, 0,
            0, 0, 9, 0, 6, 0, 0, 0, 1
        ))));
        Assertions.assertEquals(new GridModel(List.of(
            7, 4, 3, 2, 9, 8, 5, 6, 1,
            6, 5, 2, 1, 4, 7, 8, 3, 9,
            9, 1, 8, 6, 3, 5, 2, 7, 4,
            5, 9, 6, 4, 7, 1, 3, 2, 8,
            8, 7, 4, 3, 2, 9, 6, 1, 5,
            3, 2, 1, 8, 5, 6, 4, 9, 7,
            2, 6, 5, 9, 1, 4, 7, 8, 3,
            1, 8, 7, 5, 6, 3, 9, 4, 2,
            4, 3, 9, 7, 8, 2, 1, 5, 6
        )), controller.solve(new GridModel(List.of(
            0, 0, 0, 0, 0, 8, 0, 6, 1,
            6, 0, 2, 0, 4, 0, 8, 0, 0,
            9, 0, 0, 0, 0, 5, 0, 0, 0,
            5, 9, 0, 4, 0, 0, 0, 0, 8,
            0, 7, 0, 0, 0, 0, 0, 1, 0,
            3, 0, 0, 0, 0, 6, 0, 9, 7,
            0, 0, 0, 9, 0, 0, 0, 0, 3,
            0, 0, 7, 0, 6, 0, 9, 0, 2,
            4, 3, 0, 7, 0, 0, 0, 0, 0
        ))));
    }
}
