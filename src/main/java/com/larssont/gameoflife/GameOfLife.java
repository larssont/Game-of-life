package com.larssont.gameoflife;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This GameOfLife program simulates Conway's Game of Life with visualization using JavaFX.
 * Allows for adjusting game logic, grid size, tick rate and others.
 *
 * @author Tommy Larsson
 * @author larssont.com
 */
public class GameOfLife extends Application {
    private static final String WINDOW_TITLE = "Conway's Game of Life";

    private static final int TICK_RATE = 8;
    private static final int GRID_ROWS = 20;
    private static final int GRID_COLUMNS = 20;
    private static final int GRID_PANE_SIZE = 600;

    private static Game game;

    /**
     * Starts program, gets called after the system is ready for the application.
     * Initializes needed objects and creates application window.
     *
     * @param stage primary stage created by platform
     */
    @Override
    public void start(Stage stage) {
        game = new Game();

        Cell[][] cells = new Cell[GRID_ROWS][GRID_COLUMNS];
        CellGridPane cellGridPane = new CellGridPane(GRID_PANE_SIZE);
        cellGridPane.populate(cells);

        Timeline timeline = createTimeLine(cells);
        HBox buttonBox = createButtons(timeline, cells);

        createWindow(stage, cellGridPane, buttonBox);
    }

    /**
     * Creates animation TimeLine for cells to update in.
     *
     * @param cells 2D array of cells to be updated in each KeyFrame update.
     * @return created timeline
     */
    private Timeline createTimeLine(Cell[][] cells) {
        KeyFrame k = new KeyFrame(Duration.millis(1000 / (float) TICK_RATE), e -> game.update(cells));
        Timeline timeline = new Timeline(k);

        timeline.setCycleCount(Timeline.INDEFINITE);

        return timeline;
    }

    /**
     * Creates HBox of buttons for play, stop, reset
     *
     * @param timeline affected timeline for button actions
     * @param cells    2D array of cells for reset
     * @return HBox created buttons
     */
    private HBox createButtons(Timeline timeline, Cell[][] cells) {
        Button playButton = new GUIButton("Play", event -> timeline.play());
        Button stopButton = new GUIButton("Stop", event -> timeline.stop());
        Button resetButton = new GUIButton("Reset", event -> {
            timeline.stop();
            game.reset(cells);
        });

        HBox box = new HBox(20, playButton, stopButton, resetButton);

        box.setPadding(new Insets(0, 0, 20, 0));
        box.setAlignment(Pos.CENTER);

        return box;
    }

    /**
     * Creates window and sets basic structure of interface.
     *
     * @param stage  for hosting scene
     * @param center center Node in window
     * @param bottom bottom Node in window
     */
    private void createWindow(Stage stage, Node center, Node bottom) {
        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(center);
        borderPane.setBottom(bottom);
        borderPane.setBackground(new Background(new BackgroundFill(Color.DIMGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(borderPane);

        stage.setTitle(WINDOW_TITLE);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    /**
     * Main method
     *
     * @param args default args
     */
    public static void main(String[] args) {
        launch(args);
    }
} 