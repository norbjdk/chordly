package com.chordly.desktop.component;

import com.chordly.desktop.manager.AppManager;
import com.chordly.desktop.model.dto.internal.ChangeViewRequest;
import com.chordly.desktop.model.dto.internal.OpenProjectRequest;
import com.chordly.desktop.model.event.EventBus;
import com.chordly.desktop.model.event.OpenProjectEvent;
import com.chordly.desktop.model.ui.ViewName;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import static com.chordly.desktop.util.ButtonFactory.addIcon;
import static com.chordly.desktop.util.ButtonFactory.createButton;

public class NavigationBar extends HBox {
    private AppManager appManager;
    private final ArrayList<Button> buttons = new ArrayList<>();

    private Button homeBtn;
    private Button newProjectBtn;
    private Button openProjectBtn;
    private Button collectionBtn;
    private Button learnBtn;
    private Button currentProjectBtn;

    public NavigationBar() {
        getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/chordly/desktop/style/navbar.css")).toExternalForm());

        initButtons();
        setupButtons();
        setupLayout();
        setupEventHandlers();
    }

    private void initButtons() {
        String [] buttonStyles = new String[]{"nav-button"};

        homeBtn = createButton("Home", "home", "Switch to home", buttonStyles, buttons);
        newProjectBtn = createButton("New Project", "new-project", "Create new project", buttonStyles, buttons);
        openProjectBtn = createButton("Open Project", "open-project", "Open any project", buttonStyles, buttons);
        currentProjectBtn = createButton("", "current-project", "See your current project", buttonStyles, buttons);
        collectionBtn = createButton("Collection", "collection", "See your projects", buttonStyles, buttons);
        learnBtn = createButton("Learn", "learn", "Learn about chordly", buttonStyles, buttons);
    }

    private void setupButtons() {
        int b = 0;

        FontAwesomeSolid[] iconPack = {
                FontAwesomeSolid.HOME,
                FontAwesomeSolid.PLUS_CIRCLE,
                FontAwesomeSolid.FOLDER_OPEN,
                FontAwesomeSolid.BOOK_OPEN,
                FontAwesomeSolid.ADDRESS_BOOK,
                FontAwesomeSolid.GRADUATION_CAP
        };

        for (Button button : buttons) {
            addIcon(button, iconPack[b], 16);
            b++;
        }
    }

    private void setupLayout() {
        this.setSpacing(5);
        this.getStyleClass().add("nav-bar");

        getChildren().addAll(
                homeBtn,
                newProjectBtn,
                openProjectBtn,
                createSpacer(),
                currentProjectBtn,
                createSpacer(),
                collectionBtn,
                learnBtn
        );
    }

    private void setupEventHandlers() {
        homeBtn.setOnAction(actionEvent -> onHomeButtonClicked());
        newProjectBtn.setOnAction(actionEvent -> onNewProjectButtonClicked());
        openProjectBtn.setOnAction(actionEvent -> onOpenProjectButtonClicked()); // DODAJ TĘ LINIĘ
    }

    private void setupEventListeners() {

    }

    private Region createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        return spacer;
    }

    private void onHomeButtonClicked() {
        if (appManager == null) {
            appManager = AppManager.getInstance();
        }
        appManager.changeView(new ChangeViewRequest(ViewName.HOME));
    }

    private void onNewProjectButtonClicked() {
        if (appManager == null) {
            appManager = AppManager.getInstance();
        }
        appManager.changeView(new ChangeViewRequest(ViewName.CREATE_PROJECT));
    }

    private void onOpenProjectButtonClicked() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MusicXML Files", "*.musicxml"));
        final File selectedFile = fileChooser.showOpenDialog(this.getScene().getWindow());
        if (selectedFile != null) {
            if (appManager == null) {
                appManager = AppManager.getInstance();
            }
            final OpenProjectRequest request = new OpenProjectRequest();
            request.setFile(selectedFile);
            appManager.openProject(request);
        }
    }
}
