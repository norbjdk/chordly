package com.chordly.desktop.component.music;

import com.chordly.desktop.model.music.Measure;
import com.chordly.desktop.model.music.Part;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.util.List;

public class StaffLinePane extends StackPane {
    private static final int WIDTH = 800;
    private static final int MEASURES_PER_LINE = 5;

    private Part part;
    private StaffPane staffPane;
    private HBox measuresContainer;

    public StaffLinePane(Part part, int startMeasure, int endMeasure) {
        this.part = part;
        initView(startMeasure, endMeasure);
    }

    private void initView(int startMeasure, int endMeasure) {
        staffPane = new StaffPane(WIDTH);

        List<Measure> measures = part.getMeasures();
        if (!measures.isEmpty() && measures.getFirst().getClef() != null) {
            staffPane.setClef(measures.getFirst().getClef(), measures.getFirst().getClefLine());
        }

        measuresContainer = new HBox(20);
        measuresContainer.setPadding(new Insets(0, 0, 0, 70));

        for (int i = startMeasure; i < endMeasure && i < measures.size(); i++) {
            Measure measure = measures.get(i);
            MeasurePane measurePane = new MeasurePane(measure);
            measuresContainer.getChildren().add(measurePane);
        }

        getChildren().addAll(staffPane, measuresContainer);
    }

    public static int getMeasuresPerLine() {
        return MEASURES_PER_LINE;
    }
}
