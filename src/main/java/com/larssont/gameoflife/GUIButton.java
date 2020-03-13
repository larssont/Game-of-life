package com.larssont.gameoflife;

import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represents a custom GUI button, extends Button from JavaFX.
 *
 * @author Tommy Larsson
 * @author larssont.com
 */
public class GUIButton extends Button {

    private static final String STYLE_SHEET = "style/button.css";
    private static final Logger LOGGER = Logger.getLogger(GUIButton.class.getName());

    /**
     * Creates a GUIButton.
     *
     * @param label         button's label
     * @param onActionEvent button's onActionEvent when activated
     */
    public GUIButton(String label, EventHandler<javafx.event.ActionEvent> onActionEvent) {
        setText(label);
        setOnAction(onActionEvent);

        try {
            getStylesheets().add(getClass().getClassLoader().getResource(STYLE_SHEET).toExternalForm());
        } catch (NullPointerException e) {
            LOGGER.log(Level.WARNING, e.toString(), e);
        }
    }
}
