package com.larssont.gameoflife;

import java.util.ArrayList;

/**
 * Contains and handles game logic.
 *
 * @author Tommy Larsson
 * @author larssont.com
 */
public class Game {

    private int neighbourRadius = 1; //Radius for cells to be considered neighbours

    private int[] cellBornConditions = {3}; //Number of neighbours required for birth
    private int[] cellSurviveConditions = {2, 3}; //Number of neighbours required for survival

    /**
     * Updates 2D array of cells to the next iteration in the game.
     * Check each cell independently for number of neighbours and sets
     * alive boolean accordingly.
     *
     * @param cells 2D array of cells
     */
    public void update(Cell[][] cells) {
        ArrayList<Cell> diedCells = new ArrayList<>();
        ArrayList<Cell> bornCells = new ArrayList<>();

        for (Cell[] row : cells) {
            for (Cell cell : row) {
                int neighbours = findNeighbours(cells, cell);

                if (cell.isAlive()) {
                    if (containsInt(cellSurviveConditions, neighbours)) {
                        continue;
                    }
                    diedCells.add(cell);
                    continue;
                }

                if (containsInt(cellBornConditions, neighbours)) {
                    bornCells.add(cell);
                }
            }
        }

        for (Cell cell : diedCells) {
            cell.setAlive(false);
        }

        for (Cell cell : bornCells) {
            cell.setAlive(true);
        }
    }

    /**
     * Finds number of neighbours for a given cell inside a given 2D array of cells.
     *
     * @param cells 2D array of cells
     * @param cell  origin cell to be checked
     * @return number of neighbours for cell
     */
    private int findNeighbours(Cell[][] cells, Cell cell) {
        int neighbours = 0;
        int row = cell.getRow();
        int col = cell.getColumn();

        for (int i = row - neighbourRadius; i <= row + neighbourRadius; i++) {
            for (int j = col - neighbourRadius; j <= col + neighbourRadius; j++) {
                if (i == row && j == col) continue;
                try {
                    if (cells[i][j].isAlive()) {
                        neighbours++;
                    }
                } catch (ArrayIndexOutOfBoundsException ignore) {
                }
            }
        }
        return neighbours;
    }

    /**
     * Sets alive to false for all cells inside of 2D cell array.
     *
     * @param cells 2D array of cells
     */
    public void reset(Cell[][] cells) {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.setAlive(false);
            }
        }
    }

    /**
     * Sets neighbourRadius to given value.
     *
     * @param neighbourRadius input integer
     */
    public void setNeighbourRadius(int neighbourRadius) {
        if (neighbourRadius <= 0) {
            throw new IllegalArgumentException("Must be set to positive value");
        }
        this.neighbourRadius = neighbourRadius;
    }

    /**
     * Sets cellBornConditions to given value.
     *
     * @param cellBornConditions input integer array
     * @throws IllegalArgumentException when input array contains negative integers.
     */
    public void setCellBornConditions(int[] cellBornConditions) {
        for (int num : cellBornConditions) {
            if (num < 0) {
                throw new IllegalArgumentException("Must only contain array of non-negative integers.");
            }
        }

        this.cellBornConditions = cellBornConditions;
    }

    /**
     * Sets cellSurviveConditions to given value.
     *
     * @param cellSurviveConditions input integer array
     * @throws IllegalArgumentException when input array contains negative integers.
     */
    public void setCellSurviveConditions(int[] cellSurviveConditions) {
        for (int num : cellSurviveConditions) {
            if (num < 0) {
                throw new IllegalArgumentException("Must only contain array of non-negative integers.");
            }
        }

        this.cellSurviveConditions = cellSurviveConditions;
    }

    /**
     * Checks if input array contains input int
     *
     * @param arr array to be checked
     * @param num int to check with
     * @return true if array contains int, otherwise false
     */
    private boolean containsInt(int[] arr, int num) {
        for (int i : arr) {
            if (i == num) return true;
        }
        return false;
    }

}
