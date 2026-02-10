package com.chordly.desktop.view;

import com.chordly.desktop.component.SheetPane;
import com.chordly.desktop.component.ToolBar;
import com.chordly.desktop.component.ToolBox;
import com.chordly.desktop.manager.ProjectManager;
import com.chordly.desktop.model.entity.ProjectEntity;
import com.chordly.desktop.model.event.EventBus;
import com.chordly.desktop.model.event.ProjectCreatedEvent;
import com.chordly.desktop.model.event.ProjectOpenedEvent;
import com.chordly.desktop.model.ui.Presentable;
import com.chordly.desktop.util.DataBindingUtil;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class ProjectView extends BorderPane implements Presentable {
    private ProjectManager projectManager = ProjectManager.getInstance();

    private ToolBar toolBar;
    private ToolBox toolBox;
    private SheetPane sheetPane;

    public ProjectView() {
        getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/chordly/desktop/style/project.css")).toExternalForm());

        initComponents();
        setupComponents();
        setupLayout();
        setupListeners();
    }

    private void initComponents() {
        toolBar = new ToolBar();
        toolBox = new ToolBox();
        sheetPane = new SheetPane();
    }

    private void setupComponents() {
        toolBar.getStyleClass().add("toolbar");
        toolBox.getStyleClass().add("toolbox");
        sheetPane.getStyleClass().add("sheet-pane");
    }

    private void setupLayout() {
        final StackPane contentContainer = new StackPane(sheetPane);
        contentContainer.setPadding(new Insets(40));

        setTop(toolBar);
        setLeft(toolBox);
        setCenter(contentContainer);
    }

    private void setupListeners() {
        EventBus.getInstance().subscribe(ProjectCreatedEvent.class, event -> {
            ProjectEntity project = event.getResponse().getProject();
            updateProjectDisplay(project);
        });

        EventBus.getInstance().subscribe(ProjectOpenedEvent.class, event -> {
            ProjectEntity project = event.getResponse().getProject();
            updateProjectDisplay(project);
        });
    }

    private void updateProjectDisplay(ProjectEntity project) {
        sheetPane.updateTitle(project.getProjectTitle());
        sheetPane.updateAlbum(project.getProjectAlbum() != null ? project.getProjectAlbum() : "");
        sheetPane.updateAuthor(project.getProjectAuthor());

        if (project.getParts() != null && !project.getParts().isEmpty()) {
            sheetPane.loadParts(project.getParts());
        } else {
            sheetPane.clear();
        }
    }


}