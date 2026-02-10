package com.chordly.desktop.model.music;

import java.util.ArrayList;
import java.util.List;

public class Measure {
    private int number;
    private List<Note> notes;
    private String clef;
    private int clefLine;
    private int divisions;
    private int beats;
    private int beatType;
    private int fifths;

    public Measure(int number) {
        this.number = number;
        this.notes = new ArrayList<>();
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }

    public List<Note> getNotes() { return notes; }
    public void setNotes(List<Note> notes) { this.notes = notes; }

    public String getClef() { return clef; }
    public void setClef(String clef) { this.clef = clef; }

    public int getClefLine() { return clefLine; }
    public void setClefLine(int clefLine) { this.clefLine = clefLine; }

    public int getDivisions() { return divisions; }
    public void setDivisions(int divisions) { this.divisions = divisions; }

    public int getBeats() { return beats; }
    public void setBeats(int beats) { this.beats = beats; }

    public int getBeatType() { return beatType; }
    public void setBeatType(int beatType) { this.beatType = beatType; }

    public int getFifths() { return fifths; }
    public void setFifths(int fifths) { this.fifths = fifths; }

    @Override
    public String toString() {
        return "Measure " + number + " (" + notes.size() + " notes)";
    }
}