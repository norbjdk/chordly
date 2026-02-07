package com.chordly.desktop.component;

import com.chordly.desktop.util.FontFactory;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class ToolBar extends VBox {
    public final FlowPane firstRow = new FlowPane();
    public ToolBar() {
        getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/chordly/desktop/style/project.css")).toExternalForm());
        setupFirstRow();
        getChildren().add(firstRow);
    }

    private void setupFirstRow() {
        Button wholeNote = new Button(FontFactory.getWholeNote());
        Button halfNote = new Button(FontFactory.getHalfNote());

        wholeNote.setFont(FontFactory.getBravura(24));
        halfNote.setFont(FontFactory.getBravura(24));

        wholeNote.getStyleClass().add("tool-btn");
        halfNote.getStyleClass().add("tool-btn");

        firstRow.getChildren().addAll(wholeNote, halfNote);
    }
}
