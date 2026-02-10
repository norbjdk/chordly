package com.chordly.desktop.component;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import com.chordly.desktop.util.FontFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Objects;

public class ToolBar extends HBox {
    private final ArrayList<Button> buttons = new ArrayList<>();

    private Button wholeNoteBtn;
    private Button halfNoteBtn;
    private Button quarterNoteBtn;
    private Button quaverNoteBtn;
    private Button semiquaverNoteBtn;
    private Button thirtyTwoNoteBtn;

    private Button wholeRestBtn;
    private Button halfRestBtn;
    private Button quarterRestBtn;
    private Button eighthRestBtn;
    private Button semiquaverRestBtn;
    private Button thirtyTwoRestBtn;

    private final Font bravuraFont;

    public ToolBar() {
        getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/chordly/desktop/style/project.css")).toExternalForm());
        bravuraFont = FontFactory.getBravura(36);
        initButtons();
        setupButtons();
        setupLayout();
        setupEventHandlers();
    }

    private void initButtons() {
        wholeNoteBtn = createButton(FontFactory.getWholeNote(), "Cała nuta", "wholeNote");
        halfNoteBtn = createButton(FontFactory.getHalfNote(), "Półnuta", "halfNote");
        quarterNoteBtn = createButton(FontFactory.getQuarterNote(), "Ćwierćnuta", "quarterNote");
        quaverNoteBtn = createButton(FontFactory.getEighthNote(), "Ósemka", "quaverNote");
        semiquaverNoteBtn = createButton(FontFactory.getSixteenthNote(), "Szesnastka", "semiquaverNote");
        thirtyTwoNoteBtn = createButton(FontFactory.getThirtySecondNote(), "Trzydziestodwójka", "thirtyTwoNote");

        wholeRestBtn = createButton(FontFactory.getWholeRest(), "Pauza całonutowa", "wholeRest");
        halfRestBtn = createButton(FontFactory.getHalfRest(), "Pauza półnutowa", "halfRest");
        quarterRestBtn = createButton(FontFactory.getQuarterRest(), "Pauza ćwierćnutowa", "quarterRest");
        eighthRestBtn = createButton(FontFactory.getEighthRest(), "Pauza ósemkowa", "eighthRest");
        semiquaverRestBtn = createButton(FontFactory.getSixteenthRest(), "Pauza szesnastkowa", "semiquaverRest");
        thirtyTwoRestBtn = createButton(FontFactory.getThirtySecondRest(), "Pauza trzydziestodwójkowa", "thirtyTwoRest");
    }

    private Button createButton(String text, String tooltip, String styleClass) {
        Button button = new Button(text);
        button.setFont(bravuraFont);
        button.setTooltip(new Tooltip(tooltip));
        button.getStyleClass().add("toolbar-button");
        buttons.add(button);
        return button;
    }

    private void setupButtons() {
        buttons.forEach(btn -> {
            btn.setMinSize(32, 32);
            btn.setPrefSize(32, 32);
        });
    }

    private void setupLayout() {
        this.setSpacing(5);
        this.getStyleClass().add("toolbar");

        this.getChildren().addAll(
                wholeNoteBtn, halfNoteBtn, quarterNoteBtn,
                quaverNoteBtn, semiquaverNoteBtn, thirtyTwoNoteBtn
        );

        addSeparator();

        this.getChildren().addAll(
                wholeRestBtn, halfRestBtn, quarterRestBtn,
                eighthRestBtn, semiquaverRestBtn, thirtyTwoRestBtn
        );

        addSeparator();
    }

    private void addSeparator() {
        javafx.scene.control.Separator separator = new javafx.scene.control.Separator();
        separator.setOrientation(javafx.geometry.Orientation.VERTICAL);
        this.getChildren().add(separator);
    }

    private void setupEventHandlers() {
        wholeNoteBtn.setOnAction(e -> onNoteButtonClicked("wholeNote"));
        halfNoteBtn.setOnAction(e -> onNoteButtonClicked("halfNote"));
        quarterNoteBtn.setOnAction(e -> onNoteButtonClicked("quarterNote"));
        quaverNoteBtn.setOnAction(e -> onNoteButtonClicked("quaverNote"));
        semiquaverNoteBtn.setOnAction(e -> onNoteButtonClicked("semiquaverNote"));
        thirtyTwoNoteBtn.setOnAction(e -> onNoteButtonClicked("thirtyTwoNote"));

        wholeRestBtn.setOnAction(e -> onRestButtonClicked("wholeRest"));
        halfRestBtn.setOnAction(e -> onRestButtonClicked("halfRest"));
        quarterRestBtn.setOnAction(e -> onRestButtonClicked("quarterRest"));
        eighthRestBtn.setOnAction(e -> onRestButtonClicked("eighthRest"));
        semiquaverRestBtn.setOnAction(e -> onRestButtonClicked("semiquaverRest"));
        thirtyTwoRestBtn.setOnAction(e -> onRestButtonClicked("thirtyTwoRest"));
    }

    private void onNoteButtonClicked(String noteType) {
        System.out.println("Wybrano nutę: " + noteType);
    }

    private void onRestButtonClicked(String restType) {
        System.out.println("Wybrano pauzę: " + restType);
    }


    public Button getWholeNoteBtn() { return wholeNoteBtn; }
    public Button getHalfNoteBtn() { return halfNoteBtn; }
    public Button getQuarterNoteBtn() { return quarterNoteBtn; }
    public Button getQuaverNoteBtn() { return quaverNoteBtn; }
    public Button getSemiquaverNoteBtn() { return semiquaverNoteBtn; }
    public Button getThirtyTwoNoteBtn() { return thirtyTwoNoteBtn; }

    public Button getWholeRestBtn() { return wholeRestBtn; }
    public Button getHalfRestBtn() { return halfRestBtn; }
    public Button getQuarterRestBtn() { return quarterRestBtn; }
    public Button getEighthRestBtn() { return eighthRestBtn; }
    public Button getSemiquaverRestBtn() { return semiquaverRestBtn; }
    public Button getThirtyTwoRestBtn() { return thirtyTwoRestBtn; }

    public ArrayList<Button> getAllButtons() { return new ArrayList<>(buttons); }
}