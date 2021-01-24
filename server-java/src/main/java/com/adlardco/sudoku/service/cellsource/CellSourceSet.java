package com.adlardco.sudoku.service.cellsource;

import com.adlardco.sudoku.service.cell.Cell;
import lombok.NonNull;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class CellSourceSet implements CellSource {

    private final List<CellSource> sources;

    public CellSourceSet(@NonNull List<CellSource> sources) {
        checkSources(sources);

        this.sources = sources;
    }

    public CellSourceSet(CellSource... sources) {
        this(Arrays.asList(sources));
    }

    @Override public Cell getCell() {
        return sources.get(0).getCell();
    }

    @Override public Set<Cell> getCells() {
        return sources.stream().flatMap(source -> source.getCells().stream()).collect(Collectors.toSet());
    }

    private static void checkSources(List<CellSource> sources) {
        var firstCell = sources.get(0).getCell();
        checkNotNull(firstCell, "First cell is null");
        checkArgument(sources.stream().allMatch(source -> firstCell.equals(source.getCell())),
                "Not all sources reference the same cell");
    }
}
