package com.adlardco.sudoku.service.cellsource;

import com.adlardco.sudoku.service.cell.Cell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CellSourceSetTest {

    private List<CellSource> sources;
    private CellSource source;
    private List<Cell> cells;

    @BeforeEach
    public void setup() {
        sources = List.of(mock(CellSource.class), mock(CellSource.class));
        cells = IntStream.range(0, 4).mapToObj(Cell::of).collect(Collectors.toList());
        when(sources.get(0).getCells()).thenReturn(Set.of(cells.get(0), cells.get(1)));
        when(sources.get(1).getCells()).thenReturn(Set.of(cells.get(0), cells.get(2)));
        when(sources.get(0).getCell()).thenReturn(cells.get(0));
        when(sources.get(1).getCell()).thenReturn(cells.get(0));
        source = new CellSourceSet(sources);
    }

    @Test
    public void testGetCells() {
        assertEquals(Set.of(Cell.of(0), Cell.of(1), Cell.of(2)), source.getCells());
        sources.forEach(source -> verify(source, times(1)).getCells());
    }

    @Test
    public void testGetCell() {
        Assertions.assertEquals(cells.get(0), source.getCell());
        verify(sources.get(0), times(3)).getCell();
    }
}
