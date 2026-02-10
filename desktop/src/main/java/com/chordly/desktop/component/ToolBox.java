package com.chordly.desktop.component;

import com.chordly.desktop.util.ButtonFactory;
import com.chordly.desktop.util.FontFactory;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Objects;

public class ToolBox extends VBox {
    private final ArrayList<Button> buttons = new ArrayList<>();

    private Button gClefBtn;
    private Button fClefBtn;
    private Button cClefBtn;

    private Button sharpBtn;
    private Button flatBtn;
    private Button naturalBtn;
    private Button doubleSharpBtn;
    private Button doubleFlatBtn;

    private Button pppBtn;
    private Button ppBtn;
    private Button pBtn;
    private Button fffBtn;
    private Button ffBtn;
    private Button fBtn;
    private Button mpBtn;
    private Button mfBtn;

    private final Font bravuraFont;

    public ToolBox () {
        getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/chordly/desktop/style/project.css")).toExternalForm());
        bravuraFont = FontFactory.getBravura(36);
        initButtons();
        setupButtons();
        setupLayout();
    }

    private void initButtons() {
        final String [] classStyles = new String[] {"toolbox-button"};

        gClefBtn = ButtonFactory.createButton(FontFactory.getGClef(), "gClef", "Treble Clef", classStyles, bravuraFont, buttons);
        fClefBtn = ButtonFactory.createButton(FontFactory.getFClef(), "fClef", "Bass Clef", classStyles, bravuraFont, buttons);
        cClefBtn = ButtonFactory.createButton(FontFactory.getGClef(), "cClef", "Alto Clef", classStyles, bravuraFont, buttons);

        sharpBtn = ButtonFactory.createButton(FontFactory.getSharp(), "sharp", "Sharp", classStyles, bravuraFont, buttons);
        flatBtn = ButtonFactory.createButton(FontFactory.getFlat(), "flat", "Flat", classStyles, bravuraFont, buttons);
        naturalBtn = ButtonFactory.createButton(FontFactory.getNatural(), "natural", "Natural", classStyles, bravuraFont, buttons);
        doubleSharpBtn = ButtonFactory.createButton(FontFactory.getDoubleSharp(), "double-sharp", "Double Sharp", classStyles, bravuraFont, buttons);
        doubleFlatBtn = ButtonFactory.createButton(FontFactory.getDoubleFlat(), "double-flat", "Double Flat", classStyles, bravuraFont, buttons);

        pBtn = ButtonFactory.createButton(FontFactory.getDynamic(FontFactory.Dynamic.P), "p", "P", classStyles, bravuraFont, buttons);
        fBtn = ButtonFactory.createButton(FontFactory.getDynamic(FontFactory.Dynamic.F), "f", "F", classStyles, bravuraFont, buttons);
        mpBtn = ButtonFactory.createButton(FontFactory.getDynamic(FontFactory.Dynamic.MP), "mp", "MP", classStyles, bravuraFont, buttons);
        mfBtn = ButtonFactory.createButton(FontFactory.getDynamic(FontFactory.Dynamic.MF), "mf", "MF", classStyles, bravuraFont, buttons);
    }

    private void setupButtons() {
        buttons.forEach(btn -> {
            btn.setPrefSize(24, 24);
            btn.setMinSize(24, 24);
        });
    }

    private void setupLayout() {
        this.setSpacing(5);
        this.getStyleClass().add("toolbox");

        final Accordion accordion = new Accordion();

        final TitledPane clefsPane = new TitledPane("Clefs", null);
        final TitledPane accidentalsPane = new TitledPane("Accidentals", null);
        final TitledPane dynamicsPane = new TitledPane("Dynamics", null);

        final GridPane clefsContainer = new GridPane();
        final GridPane accidentalsContainer = new GridPane();
        final GridPane dynamicsContainer = new GridPane();

        clefsContainer.getStyleClass().add("toolbox-container");
        accidentalsContainer.getStyleClass().add("toolbox-container");
        dynamicsContainer.getStyleClass().add("toolbox-container");

        clefsContainer.add(gClefBtn, 0, 0);
        clefsContainer.add(fClefBtn, 1, 0);
        clefsContainer.add(cClefBtn, 0, 1);

        accidentalsContainer.add(sharpBtn, 0, 0);
        accidentalsContainer.add(flatBtn, 1, 0);
        accidentalsContainer.add(naturalBtn, 0, 1);
        accidentalsContainer.add(doubleSharpBtn, 1, 1);
        accidentalsContainer.add(doubleFlatBtn,0, 2);

        dynamicsContainer.add(pBtn, 0, 0);
        dynamicsContainer.add(fBtn, 1, 0);
        dynamicsContainer.add(mpBtn, 0, 1);
        dynamicsContainer.add(mfBtn, 1, 1);

        clefsPane.setContent(clefsContainer);
        clefsPane.setExpanded(true);

        accidentalsPane.setContent(accidentalsContainer);
        accidentalsPane.setExpanded(true);

        dynamicsPane.setContent(dynamicsContainer);
        dynamicsPane.setExpanded(true);

        accordion.getPanes().addAll(
                clefsPane,
                accidentalsPane,
                dynamicsPane
        );

        this.getChildren().add(accordion);
    }
    private void setupEventHandlers() {}
}
