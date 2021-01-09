package com.adlardco.sudoku.service.option;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public final class OptionId {

    private final int cellIndex;
    private final int option;
}
