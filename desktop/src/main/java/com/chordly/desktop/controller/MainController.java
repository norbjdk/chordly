package com.chordly.desktop.controller;

import com.chordly.desktop.component.NavigationBar;
import com.chordly.desktop.component.SideBar;
import com.chordly.desktop.model.event.ChangeViewEvent;
import com.chordly.desktop.model.event.EventBus;
import com.chordly.desktop.view.HomeView;
import com.chordly.desktop.view.NewProjectView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML private BorderPane root;

    private final NavigationBar navigationBar = new NavigationBar();
    private final SideBar sideBar = new SideBar();

    private final HBox navContainer = new HBox();
    private final StackPane viewContainer = new StackPane();
    private final BorderPane layoutContainer = new BorderPane();

    private final HomeView homeView = new HomeView();
    private final NewProjectView newProjectView = new NewProjectView();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupLayout();
        applyListeners();
        changeView("home");
    }

    private void setupLayout() {
        HBox.setHgrow(navigationBar, Priority.ALWAYS);
        navContainer.getChildren().add(navigationBar);
        layoutContainer.setTop(navContainer);

        viewContainer.setPadding(new Insets(25, 25, 25 ,25));
        root.setCenter(layoutContainer);
        layoutContainer.setCenter(viewContainer);

        VBox sideContainer = new VBox();
        sideContainer.getChildren().add(sideBar);
        VBox.setVgrow(sideBar, Priority.ALWAYS);
        VBox.setVgrow(sideContainer, Priority.ALWAYS);
        root.setRight(sideContainer);
    }

    private void changeView(String view) {
        if (view.isEmpty()) return;
        viewContainer.getChildren().clear();
        switch (view) {
            case "home" -> viewContainer.getChildren().add(homeView);
            case "new-project" -> viewContainer.getChildren().add(newProjectView);
        }
    }

    private void applyListeners() {
        EventBus.getInstance().subscribe(ChangeViewEvent.class, event -> changeView(event.getViewName()));
    }
}
