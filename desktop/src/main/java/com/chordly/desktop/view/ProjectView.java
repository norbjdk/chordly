package com.chordly.desktop.view;

import com.chordly.desktop.component.SheetComponent;
import com.chordly.desktop.component.ToolBar;
import com.chordly.desktop.component.ToolBox;
import com.chordly.desktop.model.dto.internal.SheetData;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.Objects;


public class ProjectView extends BorderPane {
    private final ToolBox toolBox = new ToolBox();
    private final ToolBar toolBar = new ToolBar();
    private final SheetComponent sheetComponent = new SheetComponent(new SheetData());
    private final ScrollPane centerPane = new ScrollPane();
    private final VBox contentContainer = new VBox();
    public ProjectView() {
        getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/chordly/desktop/style/project.css")).toExternalForm());
//        setLeft(toolBox);
//        setTop(toolBar);
        setCenter(sheetComponent);
    }
}
