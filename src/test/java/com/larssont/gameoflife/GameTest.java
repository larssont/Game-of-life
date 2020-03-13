package com.larssont.gameoflife;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    private int neighbourRadius = 1;
    private int[] bornConditions = {3};
    private int[] surviveConditions = {2, 3};

    private Game game;
    private CellGridPane cellGridPane;

    @Before
    public void init() {
        cellGridPane = new CellGridPane(1000);

        game = new Game();
        game.setNeighbourRadius(neighbourRadius);
        game.setCellBornConditions(bornConditions);
        game.setCellSurviveConditions(surviveConditions);
    }

    @Test
    public void setNeighbourRadiusToPositiveValue() {
        game.setNeighbourRadius(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNeighbourRadiusToZero() {
        game.setNeighbourRadius(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setNeighbourRadiusToNegativeValue() {
        game.setNeighbourRadius(-1);
    }

    @Test
    public void resetKillsAllCells() {
        int[][] testBlueprint = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
        };

        int[][] expectedBlueprint = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},
        };

        Cell[][] testCells = createCellsFromBlueprint(testBlueprint);
        Cell[][] expectedCells = createCellsFromBlueprint(expectedBlueprint);

        game.reset(testCells);

        assertTrue(isCellsArrAliveEqual(expectedCells, testCells));
    }

    @Test
    public void updateGameWithDeadCellsDoesNothing() {
        int[][] testBlueprint = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},
        };

        int[][] expectedBlueprint = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},
        };

        Cell[][] testCells = createCellsFromBlueprint(testBlueprint);
        Cell[][] expectedCells = createCellsFromBlueprint(expectedBlueprint);

        game.update(testCells);

        assertTrue(isCellsArrAliveEqual(expectedCells, testCells));
    }

    @Test
    public void updateGameWithCornersAliveKillsAllCells() {
        int[][] testBlueprint = {
                {1, 0, 1},
                {0, 0, 0},
                {1, 0, 1},
        };

        int[][] expectedBlueprint = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0},
        };

        Cell[][] testCells = createCellsFromBlueprint(testBlueprint);
        Cell[][] expectedCells = createCellsFromBlueprint(expectedBlueprint);

        game.update(testCells);

        assertTrue(isCellsArrAliveEqual(expectedCells, testCells));
    }

    @Test
    public void updateGameWithStillLifeBlock() {
        int[][] testBlueprint = {
                {0, 0, 0, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0},
        };

        Cell[][] testCells = createCellsFromBlueprint(testBlueprint);
        Cell[][] expectedCells = createCellsFromBlueprint(testBlueprint);

        game.update(testCells);

        assertTrue(isCellsArrAliveEqual(expectedCells, testCells));
    }

    @Test
    public void updateGameWithOscillatorBlinker() {
        int[][] testBlueprint = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
        };

        int[][] expectedBlueprint = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
        };

        Cell[][] testCells = createCellsFromBlueprint(testBlueprint);
        Cell[][] expectedCells = createCellsFromBlueprint(expectedBlueprint);

        game.update(testCells);

        assertTrue(isCellsArrAliveEqual(expectedCells, testCells));

    }

    @Test
    public void updateGameWithOscillatorBeacon() {
        int[][] expectedBlueprint = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
        };

        int[][] testBlueprint = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
        };

        Cell[][] testCells = createCellsFromBlueprint(testBlueprint);
        Cell[][] expectedCells = createCellsFromBlueprint(expectedBlueprint);

        game.update(testCells);

        assertTrue(isCellsArrAliveEqual(expectedCells, testCells));

    }


    /**
     * Creates a 2D array of cells based on blueprint int 2D array
     *
     * @param blueprint 2D int array to be used as blueprint
     * @return cells of blueprint
     */
    private Cell[][] createCellsFromBlueprint(int[][] blueprint) {
        Cell[][] cells = new Cell[blueprint.length][blueprint[0].length];
        cellGridPane.populate(cells);

        for (int i = 0; i < blueprint.length; i++) {
            for (int j = 0; j < blueprint[i].length; j++) {
                if (blueprint[i][j] == 1) {
                    cells[i][j].setAlive(true);
                    continue;
                }
                cells[i][j].setAlive(false);
            }
        }

        return cells;
    }

    /**
     * Compares two 2D cell arrays and compares for equal alive state between elements
     *
     * @param cells      first 2D cell array
     * @param otherCells second 2D cell array
     * @return true if the arrays have equal alive values for each element, otherwise false
     */
    private boolean isCellsArrAliveEqual(Cell[][] cells, Cell[][] otherCells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].isAlive() != otherCells[i][j].isAlive()) return false;
            }
        }
        return true;
    }

}