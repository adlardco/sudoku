package com.adlardco.sudoku.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApplicationConfig {

    private final int port;
    private final int numCells;
    private final int maxIterations;
}
