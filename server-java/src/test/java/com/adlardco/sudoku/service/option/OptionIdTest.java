package com.adlardco.sudoku.service.option;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionIdTest {

    @Test
    public void testToString() {
        var optionId = new OptionId(1, 2);
        assertEquals("OptionId(cellIndex=1, option=2)", optionId.toString());
    }
}
