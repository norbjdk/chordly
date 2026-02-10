package com.chordly.desktop.component.music;

import com.chordly.desktop.model.music.Note;
import com.chordly.desktop.util.FontFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class NotePane extends StackPane {
    private static final int SIZE = 32;
    private Note note;
    private Text noteText;
    private Text accidentalText;
    private final Font font = FontFactory.getBravura(SIZE);

    public NotePane(Note note) {
        this.note = note;
        initTexts();
    }

    private void initTexts() {
        noteText = new Text(getNoteSymbol());
        noteText.setFont(font);

        if (!note.isRest() && note.getAlter() != 0) {
            accidentalText = new Text(getAccidentalSymbol());
            accidentalText.setFont(font);
            accidentalText.setTranslateX(-15);
        }

        if (accidentalText != null) {
            getChildren().addAll(accidentalText, noteText);
        } else {
            getChildren().add(noteText);
        }

        setPrefSize(40, 40);
    }

    private String getNoteSymbol() {
        if (note.isRest()) {
            return getRestSymbol();
        }

        String type = note.getType();
        if (type == null) type = "quarter";

        return switch (type.toLowerCase()) {
            case "whole" -> FontFactory.getWholeNote();
            case "half" -> FontFactory.getHalfNote();
            case "quarter" -> FontFactory.getQuarterNote();
            case "eighth" -> FontFactory.getEighthNote();
            case "16th" -> FontFactory.getSixteenthNote();
            case "32nd" -> FontFactory.getThirtySecondNote();
            case "64th" -> FontFactory.getSixtyFourthNote();
            default -> FontFactory.getQuarterNote();
        };
    }

    private String getRestSymbol() {
        String type = note.getType();
        if (type == null) type = "quarter";

        return switch (type.toLowerCase()) {
            case "whole" -> FontFactory.getWholeRest();
            case "half" -> FontFactory.getHalfRest();
            case "quarter" -> FontFactory.getQuarterRest();
            case "eighth" -> FontFactory.getEighthRest();
            case "16th" -> FontFactory.getSixteenthRest();
            case "32nd" -> FontFactory.getThirtySecondRest();
            case "64th" -> FontFactory.getSixtyFourthRest();
            default -> FontFactory.getQuarterRest();
        };
    }

    private String getAccidentalSymbol() {
        return switch (note.getAlter()) {
            case 1 -> FontFactory.getSharp();
            case -1 -> FontFactory.getFlat();
            case 2 -> FontFactory.getDoubleSharp();
            case -2 -> FontFactory.getDoubleFlat();
            default -> FontFactory.getNatural();
        };
    }

    public Note getNote() {
        return note;
    }
}
