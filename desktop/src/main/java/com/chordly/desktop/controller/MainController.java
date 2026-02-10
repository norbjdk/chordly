package com.chordly.desktop.controller;

import com.chordly.desktop.component.NavigationBar;
import com.chordly.desktop.component.SideBar;
import com.chordly.desktop.manager.AppManager;
import com.chordly.desktop.model.dto.internal.ChangeViewRequest;
import com.chordly.desktop.model.dto.internal.ChangeViewResponse;
import com.chordly.desktop.model.event.ChangeViewEvent;
import com.chordly.desktop.model.event.EventBus;
import com.chordly.desktop.model.ui.ViewName;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML private BorderPane root;

    private AppManager appManager;

    private final ArrayList<Node> views = new ArrayList<>();

    private final NavigationBar navigationBar = new NavigationBar();
    private final SideBar sideBar = new SideBar();

    private final HBox navContainer = new HBox();
    private final StackPane viewContainer = new StackPane();
    private final BorderPane layoutContainer = new BorderPane();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupLayout();
        applyListeners();

        if (appManager == null) {
            appManager = AppManager.getInstance();
        }
        appManager.changeView(new ChangeViewRequest(ViewName.HOME));

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

    private void changeView(ChangeViewResponse response) {
        viewContainer.getChildren().clear();
        viewContainer.getChildren().add(response.getView());
    }

    private void applyListeners() {
        EventBus.getInstance().subscribe(ChangeViewEvent.class, changeViewEvent -> {
            final ChangeViewResponse response = changeViewEvent.getResponse();
            changeView(response);
        });
    }
}
