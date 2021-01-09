package com.adlardco.sudoku.service.cell;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CellStoreTest {

    @Test
    public void testSaveAndRestoreTo() {
        var store = new CellStore();
        var cells = IntStream.range(0, 2).mapToObj(index -> Cell.of(index)).collect(Collectors.toList());
        store.save(cells);
        var restoredCells = new ArrayList<Cell>();
        store.restoreTo(restoredCells);
        assertEquals(cells, restoredCells);
    }
}
