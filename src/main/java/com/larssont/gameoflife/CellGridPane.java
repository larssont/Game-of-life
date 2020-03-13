package com.larssont.gameoflife;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

/**
 * Represents a CellGridPane, extends GridPane from javafx.
 *
 * @author Tommy Larsson
 * @author larssont.com
 */
public class CellGridPane extends GridPane {

    private final int gridPaneSize;

    /**
     * Creates a new CellGridPane.
     *
     * @param gridPaneSize size of gridPane height and width in px
     */
    public CellGridPane(int gridPaneSize) {
        this.gridPaneSize = gridPaneSize;
        setProps();
    }

    /**
     * Sets MaxSize, Padding, Hgap, and Vgap properties of gridPane.
     */
    private void setProps() {
        setMaxSize(gridPaneSize, gridPaneSize);
        setPadding(new Insets(20));
        setHgap(4);
        setVgap(4);
    }

    /**
     * Populates 2D array of cells with new Cells.
     *
     * @param cells 2D array of cells to be populated
     */
    public void populate(Cell[][] cells) {
        int cellSize = gridPaneSize / Math.max(cells.length, cells[0].length);

        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                Cell cell = new Cell(cellSize, row, col);
                cells[row][col] = cell;
                this.add(cell.getRect(), col, row);
            }
        }
    }
}
