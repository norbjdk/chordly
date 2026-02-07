package com.chordly.desktop.component;

import com.chordly.desktop.model.event.ChangeViewEvent;
import com.chordly.desktop.model.event.EventBus;
import com.chordly.desktop.model.ui.UIElement;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

import java.util.Objects;

import static com.chordly.desktop.util.IconTool.setIcon;

public class NavigationBar extends HBox implements UIElement {
    private final Button homeBtn = new Button("Home");
    private final Button newProjectBtn = new Button("New Project");
    private final Button openProjectBtn = new Button("Open Project");
    private final Button collectionBtn = new Button("Collection");
    private final Button learnBtn = new Button("Learn");

    private final Button[] buttons = {homeBtn, newProjectBtn, openProjectBtn, collectionBtn, learnBtn};

    public NavigationBar() {
        getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/chordly/desktop/style/navbar.css")).toExternalForm());
        getStyleClass().add("navigation-bar");
        applyEvents();
        setupButtons();
    }

    @Override
    public void applyStyle(Node target, String className) {
        target.getStyleClass().add(className);
    }

    private void setupButtons() {
        FontAwesomeSolid[] iconPack = {
                FontAwesomeSolid.HOME,
                FontAwesomeSolid.PLUS_CIRCLE,
                FontAwesomeSolid.FOLDER_OPEN,
                FontAwesomeSolid.ADDRESS_BOOK,
                FontAwesomeSolid.GRADUATION_CAP
        };

        for (int i = 0; i < buttons.length; i++) {
            setIcon(buttons[i], iconPack[i]);
            applyStyle(buttons[i], "nav-button");
            buttons[i].setMaxHeight(Double.MAX_VALUE);
        }

        Region leftSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);

        Region rightSpacer = new Region();
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

//        getChildren().add(leftSpacer);
//        getChildren().addAll(buttons);
//        getChildren().add(rightSpacer);

        getChildren().add(buttons[0]);
        getChildren().add(buttons[1]);
        getChildren().add(buttons[2]);
        getChildren().add(rightSpacer);
        getChildren().add(buttons[3]);
        getChildren().add(buttons[4]);

        setSpacing(20);
    }

    @Override
    public void applyEvents() {
        homeBtn.setOnAction(actionEvent -> EventBus.getInstance().publish(new ChangeViewEvent("home")));
        newProjectBtn.setOnAction(actionEvent -> EventBus.getInstance().publish(new ChangeViewEvent("new-project")));
        openProjectBtn.setOnAction(actionEvent -> EventBus.getInstance().publish(new ChangeViewEvent("project")));
    }

    @Override
    public void applyListeners() {

    }

    @Override
    public void applyLayout() {

    }
}