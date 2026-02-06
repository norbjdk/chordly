package com.chordly.desktop.component;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class SideBar extends GridPane {
    private final ProfileBar profileBar = new ProfileBar();

    public SideBar() {
        getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/chordly/desktop/style/sidebar.css")).toExternalForm());
        getStyleClass().add("side-bar");
        add(profileBar, 0, 0);
    }
}
