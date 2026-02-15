package com.chordly.desktop.component.music;

import com.chordly.desktop.model.music.Measure;
import com.chordly.desktop.model.music.Note;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MeasurePane extends HBox {
    private static final int SPACING = 10;
    private static final int STAFF_TOP = 20;

    private final Measure measure;
    private String clef;

    public MeasurePane(Measure measure) {
        this.measure = measure;
        this.clef = measure.getClef() != null ? measure.getClef() : "G";

        initView();
    }

    private void initView() {
        this.setSpacing(15);
        this.setAlignment(Pos.CENTER_LEFT);
        this.setMinHeight(100);
        this.setPrefHeight(100);

        if (measure.getNumber() == 1 && measure.getBeats() > 0) {
            addTimeSignature();
        }

        for (Note note : measure.getNotes()) {
            NotePane noteView = new NotePane(note);

            double yOffset = calculateNoteYPosition(note);
            noteView.setTranslateY(yOffset);

            getChildren().add(noteView);
        }

        addBarline();
    }

    private void addTimeSignature() {
        StackPane timeSignature = new StackPane();

        Text beatsText = new Text(String.valueOf(measure.getBeats()));
        Text beatTypeText = new Text(String.valueOf(measure.getBeatType()));

        Font timeFont = Font.font("Arial Black", 22);
        beatsText.setFont(timeFont);
        beatTypeText.setFont(timeFont);

        beatsText.setTranslateY(-21);
        beatTypeText.setTranslateY(1);

        timeSignature.getChildren().addAll(beatsText, beatTypeText);
        timeSignature.setPrefWidth(30);

        getChildren().add(timeSignature);
    }

    private double calculateNoteYPosition(Note note) {
        if (note.isRest()) {
            return 0;
        }

        String step = note.getStep();
        int octave = note.getOctave();

        if ("G".equals(clef)) {
            int middleC = 4;
            int positionFromMiddleC = calculatePositionFromMiddleC(step, octave, middleC);

            return -(positionFromMiddleC * (SPACING / 2.0)) + (SPACING * 3);
        }

        if ("F".equals(clef)) {
            int middleC = 4;
            int positionFromMiddleC = calculatePositionFromMiddleC(step, octave, middleC);

            return -(positionFromMiddleC * (SPACING / 2.0)) - (SPACING * 3);
        }

        return 0;
    }

    private int calculatePositionFromMiddleC(String step, int octave, int middleOctave) {
        int stepValue = switch (step) {
            case "C" -> 0;
            case "D" -> 1;
            case "E" -> 2;
            case "F" -> 3;
            case "G" -> 4;
            case "A" -> 5;
            case "B" -> 6;
            default -> 0;
        };

        int octaveDiff = octave - middleOctave;
        return (octaveDiff * 7) + stepValue;
    }

    private void addBarline() {
        Line barline = new Line();
        barline.setStartX(0);
        barline.setStartY(-40);
        barline.setEndX(0);
        barline.setEndY(40);
        barline.setStroke(Color.BLACK);
        barline.setStrokeWidth(2);

        getChildren().add(barline);
    }

    public Measure getMeasure() {
        return measure;
    }
}
