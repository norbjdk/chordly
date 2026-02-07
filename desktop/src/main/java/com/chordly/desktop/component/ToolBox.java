package com.chordly.desktop.component;

import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import com.chordly.desktop.util.FontFactory;

import java.util.Objects;

public class ToolBox extends VBox {
    private final Accordion toolsAccordion = new Accordion();
    public ToolBox() {
        getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/chordly/desktop/style/project.css")).toExternalForm());
        setupClefs();
        setupDynamics();
        getChildren().add(toolsAccordion);
    }

    private void setupClefs() {
        GridPane clefsGrid = new GridPane();
        toolsAccordion.getPanes().add(new TitledPane("Clefs", clefsGrid));

        Button clefG = new Button(FontFactory.getGClef());
        clefG.setStyle("-fx-text-fill: white");
        clefG.setFont(FontFactory.getBravura(24));

        Button clefF = new Button(FontFactory.getFClef());
        clefF.setStyle("-fx-text-fill: white");
        clefF.setFont(FontFactory.getBravura(24));

        clefsGrid.add(clefG, 0, 0);
        clefsGrid.add(clefF, 1 ,0);
    }

    private void setupDynamics() {
        GridPane dynamicsGrid = new GridPane();
        toolsAccordion.getPanes().add(new TitledPane("Dynamics", dynamicsGrid));

        Button pDynamic = new Button(FontFactory.getDynamic(FontFactory.Dynamic.P));
        Button mDynamic = new Button(FontFactory.getDynamic(FontFactory.Dynamic.M));
        Button fDynamic = new Button(FontFactory.getDynamic(FontFactory.Dynamic.F));

        pDynamic.setFont(FontFactory.getBravura(24));
        mDynamic.setFont(FontFactory.getBravura(24));
        fDynamic.setFont(FontFactory.getBravura(24));

        pDynamic.getStyleClass().add("tool-btn");
        mDynamic.getStyleClass().add("tool-btn");
        fDynamic.getStyleClass().add("tool-btn");

        dynamicsGrid.add(pDynamic, 0, 0);
        dynamicsGrid.add(mDynamic, 1, 0);
        dynamicsGrid.add(fDynamic, 0, 1);
    }
}
