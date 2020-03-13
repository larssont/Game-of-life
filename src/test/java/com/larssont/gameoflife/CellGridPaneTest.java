package com.larssont.gameoflife;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class CellGridPaneTest {

    private CellGridPane cellGridPane;

    @Before
    public void init() {
        cellGridPane = new CellGridPane(600);
    }

    @Test
    public void cellGridPaneCorrectHeight() {
        int size = 500;

        CellGridPane cellGridPane = new CellGridPane(500);

        assertEquals(cellGridPane.getMaxHeight(), size, 0);
    }

    @Test
    public void cellGridPaneCorrectWidth() {
        int size = 500;

        CellGridPane cellGridPane = new CellGridPane(500);

        assertEquals(cellGridPane.getMaxWidth(), size, 0);
    }

    @Test
    public void populateFillsWithCells() {
        int rows1 = 10;
        int columns = 10;
        Cell[][] cells = new Cell[rows1][columns];
        cellGridPane.populate(cells);

        for (Cell[] rows : cells) {
            for (Cell c : rows) assertNotNull(c);
        }
    }

}