package com.adlardco.sudoku.controller.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class GridModel {

    private List<Integer> cellValues;
}
