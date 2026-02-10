package com.chordly.desktop.view;

import com.chordly.desktop.manager.AppManager;
import com.chordly.desktop.model.dto.internal.CreateProjectRequest;
import com.chordly.desktop.model.entity.ProjectEntity;
import com.chordly.desktop.model.event.ChangeProjectPreviewEvent;
import com.chordly.desktop.model.event.EventBus;
import com.chordly.desktop.model.ui.Presentable;
import com.chordly.desktop.util.ButtonFactory;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateProjectView extends FlowPane implements Presentable {
    private AppManager appManager;
    private final ArrayList<TextField> inputs = new ArrayList<>();

    private ProjectEntity.Template template;

    private TextField titleInput;
    private TextField albumInput;
    private TextField authorInput;

    private Button createProjectBtn;
    private Canvas sheetPreview;

    public CreateProjectView() {
        getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/chordly/desktop/style/new-project.css")).toExternalForm());

        initFields();
        initButton();
        initPreview();
        setupFields();
        setupButton();
        setupLayout();
        setupPreview();
        setupEventListeners();
        setupEventHandlers();
    }

    private void initFields() {
        titleInput = new TextField();
        albumInput = new TextField();
        authorInput = new TextField();

        inputs.add(titleInput);
        inputs.add(albumInput);
        inputs.add(authorInput);
    }

    private void initButton() {
        String[] classStyles = new String[] {"create-btn"};
        createProjectBtn = ButtonFactory.createButton("Create", "create-project", "Press to create project", classStyles);
    }

    private void initPreview() {
        sheetPreview = new Canvas();
    }

    private void setupFields() {

    }

    private void setupButton() {
        ButtonFactory.addIcon(createProjectBtn, FontAwesomeSolid.CHECK_CIRCLE, 16);
    }

    private void setupPreview() {
        sheetPreview.setWidth(300);
        sheetPreview.setHeight(450);
        GraphicsContext gc = sheetPreview.getGraphicsContext2D();
        gc.setFill(Color.WHITESMOKE);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillRect(0, 0, 300, 450);
    }

    private void setupLayout() {
        getStyleClass().add("new-project-view");

        final GridPane dataForm = new GridPane();
        final GridPane templateForm = new GridPane();

        dataForm.setEffect(createReflection());
        templateForm.setEffect(createReflection());
        sheetPreview.setEffect(createReflection());

        dataForm.getStyleClass().add("data-form");
        templateForm.getStyleClass().add("template-form");

        final ArrayList<VBox> fieldBoxes = new ArrayList<>(List.of(
                new VBox(10, new Label("Title"), titleInput),
                new VBox(10, new Label("Album"), albumInput),
                new VBox(10, new Label("Author"), authorInput)
        ));

        fieldBoxes.forEach( vbox -> {
            vbox.getChildren().getFirst().getStyleClass().add("data-label");
            vbox.getChildren().getLast().getStyleClass().add("data-input");
        });

        for (int vbox = 0; vbox < fieldBoxes.size(); vbox++) {
            dataForm.add(fieldBoxes.get(vbox), 0, vbox);
        }

        final Label categoryLabel = new Label("Category");
        final Label templateLabel = new Label("Template");

        categoryLabel.getStyleClass().add("template-label");
        templateLabel.getStyleClass().add("template-label");

        final ListView<String> categoryList = new ListView<>();
        final ListView<String> templatesList = new ListView<>();

        categoryList.getItems().addAll("General", "Choral", "Solo", "Band", "Orchestral");
        templatesList.getItems().addAll("Treble Clef", "Bass Clef", "Grand Staff");

        templatesList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                switch (newValue) {
                    case "Treble Clef" -> template = ProjectEntity.Template.TREBLE;
                    case "Bass Clef" -> template = ProjectEntity.Template.BASS;
                    case "Grand Staff" -> template = ProjectEntity.Template.GRAND;
                }
            }
            EventBus.getInstance().publish(new ChangeProjectPreviewEvent());
        });

        templateForm.add(categoryLabel, 0, 0);
        templateForm.add(templateLabel, 1, 0);
        templateForm.add(categoryList, 0, 1);
        templateForm.add(templatesList, 1, 1);

        getChildren().addAll(
            dataForm,
            templateForm,
            sheetPreview,
            createProjectBtn
        );
    }

    private void setupEventHandlers() {
        createProjectBtn.setOnAction(actionEvent -> onCreateProjectButtonClicked());
        titleInput.setOnKeyTyped(keyEvent -> EventBus.getInstance().publish(new ChangeProjectPreviewEvent()));
        albumInput.setOnKeyTyped(keyEvent -> EventBus.getInstance().publish(new ChangeProjectPreviewEvent()));
        authorInput.setOnKeyTyped(keyEvent -> EventBus.getInstance().publish(new ChangeProjectPreviewEvent()));
    }

    private void setupEventListeners() {
        EventBus.getInstance().subscribe(ChangeProjectPreviewEvent.class, event -> redrawPreview());
    }

    private void onCreateProjectButtonClicked() {
        if (appManager == null) {
            appManager = AppManager.getInstance();
        }

        final String title = titleInput.getText();
        final String album = albumInput.getText();
        final String author = authorInput.getText();

        CreateProjectRequest request = new CreateProjectRequest();
        request.setTitle(title);
        request.setAlbum(album);
        request.setAuthor(author);

        appManager.createProject(request);
    }

    private void redrawPreview() {
        GraphicsContext gc = sheetPreview.getGraphicsContext2D();
        gc.clearRect(0, 0, sheetPreview.getWidth(), sheetPreview.getHeight());
        gc.setFill(Color.WHITESMOKE);
        gc.fillRect(0, 0, sheetPreview.getWidth(), sheetPreview.getHeight());
        gc.setFill(Color.BLACK);
        gc.setTextAlign(TextAlignment.CENTER);

        final String title = titleInput.getText();
        final String album = albumInput.getText();
        final String author = authorInput.getText();

        if (!title.isEmpty()) {
            gc.setFont(Font.font("Arial Black", 16));
            gc.fillText(title, sheetPreview.getWidth() / 2, 60);
        }

        if (!album.isEmpty()) {
            gc.setFont(Font.font("Roboto Light", 13));
            gc.fillText(album, sheetPreview.getWidth() / 2, 80);
        }

        if (!author.isEmpty()) {
            gc.setFont(Font.font("Times New Roman", 14));
            gc.fillText(author, sheetPreview.getWidth() - 28, 100);
        }

        if (template != null) {
            Image image;
            switch (template) {
                case TREBLE -> image = new Image(Objects.requireNonNull(getClass().getResource("/com/chordly/desktop/asset/img/treble.png")).toExternalForm());
                case BASS -> image = new Image(Objects.requireNonNull(getClass().getResource("/com/chordly/desktop/asset/img/bass.png")).toExternalForm());
                case GRAND -> image = new Image(Objects.requireNonNull(getClass().getResource("/com/chordly/desktop/asset/img/grand.png")).toExternalForm());
                case null, default -> throw new RuntimeException("Error with template");
            }
            gc.drawImage(image, 10, 120, sheetPreview.getWidth() - 30, sheetPreview.getHeight() / 2);
        }
    }

    private Reflection createReflection() {
        Reflection reflection = new Reflection();
        reflection.setFraction(0.7);
        reflection.setTopOffset(0.0);
        reflection.setTopOpacity(0.3);
        reflection.setBottomOpacity(0.0);

        return reflection;
    }
}
