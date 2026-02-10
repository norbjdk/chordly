package com.chordly.desktop.component;

import com.chordly.desktop.component.music.StaffLinePane;
import com.chordly.desktop.model.music.Part;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Objects;

public class SheetPane extends VBox {
    private final Color whiteSmoke = Color.WHITESMOKE;

    private int width = 800;
    private int height = 1000;

    private TextField titleField;
    private TextField albumField;
    private TextField authorField;
    private VBox contentBox;
    private List<Part> parts;

    private final String title = "";
    private final String album = "";
    private final String author = "";

    public SheetPane() {
        getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/chordly/desktop/style/sheet.css")).toExternalForm());
        setPrefSize(width, height);
        setMaxSize(width, height);

        initFields();
        setupFields();
        setupLayout();
    }

    private void initFields() {
        titleField = new TextField(title);
        albumField = new TextField(album);
        authorField = new TextField(author);

        contentBox = new VBox(30);
        contentBox.setPadding(new Insets(20));
        HBox.setHgrow(contentBox, Priority.ALWAYS);
        VBox.setVgrow(contentBox, Priority.ALWAYS);
    }

    public void loadParts(List<Part> parts) {
        this.parts = parts;
        renderScore();
    }

    private void setupFields() {
        titleField.getStyleClass().add("title");
        albumField.getStyleClass().add("album");
        authorField.getStyleClass().add("author");
    }

    private void setupLayout() {
        this.setSpacing(1);
        this.getStyleClass().add("sheet");

        final HBox titleBox = createHorizontalContainer(titleField, Pos.CENTER);
        final HBox albumBox = createHorizontalContainer(albumField, Pos.CENTER);
        final HBox authorBox = createHorizontalContainer(authorField, Pos.CENTER_RIGHT);

        getChildren().addAll(
                createSpacer(0.05),
                titleBox,
                albumBox,
                createSpacer(0.01),
                authorBox,
                createSpacer(0.02),
                contentBox
        );
    }

    public void updateTitle(String title) {
        Platform.runLater(() -> titleField.setText(title));
    }

    public void updateAlbum(String album) {
        Platform.runLater(() -> albumField.setText(album));
    }

    public void updateAuthor(String author) {
        Platform.runLater(() -> authorField.setText(author));
    }

    private void renderScore() {
        contentBox.getChildren().clear();

        if (parts == null || parts.isEmpty()) {
            return;
        }

        Part firstPart = parts.getFirst();
        int totalMeasures = firstPart.getMeasures().size();
        int measuresPerLine = StaffLinePane.getMeasuresPerLine();

        for (int i = 0; i < totalMeasures; i += measuresPerLine) {
            int endMeasure = Math.min(i + measuresPerLine, totalMeasures);
            StaffLinePane staffLinePane = new StaffLinePane(firstPart, i, endMeasure);
            contentBox.getChildren().add(staffLinePane);
        }
    }

    private HBox createHorizontalContainer(Node child, Pos position) {
        HBox hBox = new HBox();
        hBox.getChildren().add(child);
        hBox.setAlignment(position);
        HBox.setHgrow(hBox, Priority.ALWAYS);

        return hBox;
    }

    private Region createSpacer(double coefficient) {
        Region region = new Region();
        region.setPrefHeight(coefficient * height);

        return region;
    }

    public void clear() {
        contentBox.getChildren().clear();
    }
}