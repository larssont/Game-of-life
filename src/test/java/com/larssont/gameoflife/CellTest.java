package com.larssont.gameoflife;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class CellTest {

    private Cell cell;
    private int size = 20;
    private int row = 3;
    private int column = 3;

    @Before
    public void init() {
        cell = new Cell(size, row, column);
    }

    @Test
    public void cellRectangleIsSquare() {
        assertEquals(cell.getRect().getHeight(), cell.getRect().getWidth(), 0);
    }

    @Test
    public void cellRectangleIsCorrectWidth() {
        assertEquals(cell.getRect().getWidth(), size, 0);
    }

    @Test
    public void cellRectangleIsCorrectHeight() {
        assertEquals(cell.getRect().getWidth(), size, 0);
    }

    @Test
    public void cellSetGetAliveTrue() {
        cell.setAlive(true);
        assertTrue(cell.isAlive());
    }

    @Test
    public void cellSetGetAliveFalse() {
        cell.setAlive(false);
        assertFalse(cell.isAlive());
    }
}