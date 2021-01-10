package com.adlardco.sudoku.service.option;

import com.adlardco.sudoku.service.cell.Cell;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionIteratorTest {

    @Test
    public void testIteration() {
        var iterator = new OptionIterator(List.of(
            Cell.of(3, 1),
            Cell.of(5),
            Cell.of(4, 3),
            Cell.of(1)
        ));

        assertEquals(new OptionId(0, 1), iterator.get());
        assertEquals(new OptionId(0, 3), iterator.get());
        assertEquals(new OptionId(2, 3), iterator.get());
        assertEquals(new OptionId(2, 4), iterator.get());
        assertEquals(new OptionId(0, 1), iterator.get());
    }
}
