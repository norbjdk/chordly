package com.chordly.desktop.util;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.ArrayList;
import java.util.Arrays;

public class ButtonFactory {
    private ButtonFactory() {}

    public static Button createButton(String text, String id) {
        Button button = new Button(text);
        button.setId(id);
        return button;
    }

    public static Button createButton(String text, String id, String tooltip) {
        Button button = createButton(text, id);
        button.setTooltip(new Tooltip(tooltip));
        return button;
    }

    public static Button createButton(String text, String id, String [] classStyles) {
        Button button = createButton(text, id);
        button.getStyleClass().addAll(Arrays.asList(classStyles));
        return button;
    }

    public static Button createButton(String text, String id, String tooltip, String [] classStyles) {
        Button button = new Button(text);
        button.setId(id);
        button.setTooltip(new Tooltip(tooltip));
        button.getStyleClass().addAll(Arrays.asList(classStyles));
        return button;
    }

    public static Button createButton(String text, String id, String tooltip, String [] classStyles, Font font) {
        Button button = createButton(text, id, tooltip, classStyles);
        button.setFont(font);
        return button;
    }

    public static Button createButton(String text, String id, String tooltip, String [] classStyles , ArrayList<Button> buttons) {
        Button button = createButton(text, id, tooltip, classStyles);
        buttons.add(button);
        return button;
    }

    public static Button createButton(String text, String id, String tooltip, String [] classStyles, Font font, ArrayList<Button> buttons) {
        Button button = createButton(text, id, tooltip, classStyles, font);
        buttons.add(button);
        return button;
    }

    public static void addIcon(Button button, FontAwesomeSolid solid, int size) {
        FontIcon icon = new FontIcon(solid);
        icon.setIconSize(size);
        icon.setIconColor(Color.rgb(235, 94, 40));
        button.setGraphic(icon);
        button.setContentDisplay(ContentDisplay.LEFT);
        button.setGraphicTextGap(10);
    }
}
