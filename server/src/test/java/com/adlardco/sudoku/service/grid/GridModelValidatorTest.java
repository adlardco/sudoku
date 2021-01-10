package com.adlardco.sudoku.service.grid;

import com.adlardco.sudoku.config.ApplicationConfig;
import com.adlardco.sudoku.controller.model.GridModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GridModelValidatorTest {

    private ApplicationConfig config;

    @BeforeEach
    public void setup() {
        config = mock(ApplicationConfig.class);
        when(config.getNumCells()).thenReturn(81);
    }

    @Test
    public void testIsValidWithValidValues() {
        var validator = new GridModelValidator(config);
        assertTrue(validator.isValid(new GridModel(List.of(
            1, 2, 3, 4, 5, 6, 7, 8, 9,
            1, 2, 3, 4, 5, 6, 7, 8, 9,
            1, 2, 3, 4, 5, 6, 7, 8, 9,
            1, 2, 3, 4, 5, 6, 7, 8, 9,
            1, 2, 3, 4, 0, 6, 7, 8, 9,
            1, 2, 3, 4, 5, 6, 7, 8, 9,
            1, 2, 3, 4, 5, 6, 7, 8, 9,
            1, 2, 3, 4, 5, 6, 7, 8, 9,
            1, 2, 3, 4, 5, 6, 7, 8, 9
        )), null));
    }

    @Test
    public void testIsInvalidWithNullValues() {
        var validator = new GridModelValidator(config);
        assertFalse(validator.isValid(new GridModel(null), null));
    }

    @Test
    public void testIsInvalidWithTooFewValues() {
        var validator = new GridModelValidator(config);
        assertFalse(validator.isValid(new GridModel(List.of(1, 2)), null));
    }

    @Test
    public void testIsInvalidWithInvalidValues() {
        var validator = new GridModelValidator(config);
        assertFalse(validator.isValid(new GridModel(List.of(
            1, 2, 3, 4, 5, 6, 0, 8, 9,
            1, 2, 3, 4, 5, 6, 0, 8, 9,
            1, 2, 3, 4, 5, 6, 0, 8, 9,
            1, 2, 3, 4, 5, 6, 0, 8, 9,
            1, 2, 3, 0, 5, 6, 0, 8, 9,
            1, 2, 3, 4, 5, 6, 0, 8, 9,
            1, 2, 3, 4, 5, 6, 0, 8, 9,
            1, 2, 3, 4, 5, 6, 0, 8, 9,
            1, 2, 3, 4, 5, 6, 0, 8, 10
        )), null));
    }
}
