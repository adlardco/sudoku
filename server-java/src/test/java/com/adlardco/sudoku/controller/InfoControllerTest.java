package com.adlardco.sudoku.controller;

import com.adlardco.sudoku.controller.model.InfoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InfoControllerTest {

    private InfoController controller;

    @BeforeEach
    public void setup() {
        controller = new InfoController();
    }

    @Test
    public void testInfo() {
        assertEquals(new InfoModel("UP"), controller.info());
    }
}
