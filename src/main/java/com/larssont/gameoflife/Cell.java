package com.larssont.gameoflife;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents a cell.
 *
 * @author Tommy Larsson
 * @author larssont.com
 */
public class Cell {

    private final int column;
    private final int row;

    private Rectangle rect;
    private boolean alive;

    private static final Color DEAD_COLOR = Color.WHITESMOKE;
    private static final Color ALIVE_COLOR = Color.BLACK;

    /**
     * Creates a cell.
     *
     * @param size   size of cell rectangle side
     * @param row    row position of cell
     * @param column column position of cell
     */
    public Cell(int size, int row, int column) {
        this.row = row;
        this.column = column;

        rect = new Rectangle(size, size);
        rect.setOnMouseClicked(e -> toggleAlive());
        setAlive(false);
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Rectangle getRect() {
        return rect;
    }

    public boolean isAlive() {
        return alive;
    }

    /**
     * Toggles the state of alive for cell.
     * false -> true or true -> false
     */
    private void toggleAlive() {
        alive = !alive;
        setAlive(alive);
    }

    /**
     * Sets alive state of cell and changes color of cell's rectangle.
     *
     * @param alive new alive state
     */
    public void setAlive(boolean alive) {
        this.alive = alive;

        if (alive) {
            rect.setFill(ALIVE_COLOR);
            return;
        }
        rect.setFill(DEAD_COLOR);
    }
}
