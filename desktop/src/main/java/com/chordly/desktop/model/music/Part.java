package com.chordly.desktop.model.music;

import java.util.ArrayList;
import java.util.List;

public class Part {
    private String id;
    private String name;
    private List<Measure> measures;

    public Part(String id, String name) {
        this.id = id;
        this.name = name;
        this.measures = new ArrayList<>();
    }

    public void addMeasure(Measure measure) {
        measures.add(measure);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Measure> getMeasures() { return measures; }
    public void setMeasures(List<Measure> measures) { this.measures = measures; }

    @Override
    public String toString() {
        return name + " (" + measures.size() + " measures)";
    }
}