package com.adlardco.sudoku.service.option;

import lombok.AllArgsConstructor;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
public class OptionSetFactory {

    private final int numOptions;

    public OptionSet get() {
        var options = IntStream.rangeClosed(1, numOptions).boxed().collect(Collectors.toSet());
        return new OptionSet(options);
    }
}
