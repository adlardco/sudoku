package com.adlardco.sudoku.controller;

import com.adlardco.sudoku.controller.model.InfoModel;

public class InfoController {

    public InfoModel info() {
        return new InfoModel("UP");
    }
}
