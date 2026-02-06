package com.chordly.desktop.view;

import com.chordly.desktop.model.dto.internal.SheetData;
import com.chordly.desktop.model.event.ChangeProjectPreviewEvent;
import com.chordly.desktop.model.event.ChangeProjectTitleEvent;
import com.chordly.desktop.model.event.EventBus;
import com.chordly.desktop.model.ui.UIElement;
import javafx.scene.Node;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;

import java.util.Objects;

import static com.chordly.desktop.util.IconTool.setIcon;

public class NewProjectView extends FlowPane implements UIElement {
    private final SheetData data = new SheetData();
    private final GridPane dataForm = new GridPane();
    private final GridPane templateForm = new GridPane();
    private final Canvas sheetPreview = new Canvas();

    private final TextField titleInput = new TextField();
    private final TextField subtitleInput = new TextField();
    private final TextField composerInput = new TextField();
    private final TextField wordsAuthorInput = new TextField();

    public NewProjectView() {
        getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/chordly/desktop/style/new-project.css")).toExternalForm());
        getStyleClass().add("new-project-view");

        applyEvents();
        applyListeners();

        addDataForm();
        addTemplateForm();
        addSheetPreview();

        Button createProjectBtn = new Button("Create");
        createProjectBtn.setId("create-btn");
        setIcon(createProjectBtn, FontAwesomeSolid.CHECK_CIRCLE);
        getChildren().add(createProjectBtn);
    }

    void addDataForm() {
        dataForm.getStyleClass().add("data-form");
        dataForm.setEffect(getReflection());

        VBox[] boxes = {
                new VBox(10, new Label("Title: "), titleInput),
                new VBox(10, new Label("Subtitle: "), subtitleInput),
                new VBox(10, new Label("Composer: "), composerInput),
                new VBox(10, new Label("Words Author: "), wordsAuthorInput)
        };

        for (VBox vBox : boxes) {
            vBox.getChildren().getFirst().getStyleClass().add("data-label");
            vBox.getChildren().getLast().getStyleClass().add("data-input");
        }

        for (int i = 0; i < boxes.length; i++) {
            dataForm.add(boxes[i], 0, i);
        }
        getChildren().add(dataForm);
    }
    void addTemplateForm() {
        templateForm.getStyleClass().add("template-form");
        templateForm.setEffect(getReflection());

        Label categoryLabel = new Label("Category");
        Label templateLabel = new Label("Template");

        categoryLabel.getStyleClass().add("template-label");
        templateLabel.getStyleClass().add("template-label");

        templateForm.add(categoryLabel, 0, 0);
        templateForm.add(templateLabel, 1 ,0);

        ListView<String> categoryList = new ListView<>();
        ListView<String> templatesList = new ListView<>();
        categoryList.getItems().addAll("General", "Choral", "Solo", "Band", "Orchestral");
        templatesList.getItems().addAll("Treble Clef", "Bass Clef", "Grand Staff");

        templatesList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                switch (newValue) {
                    case "Treble Clef" -> data.setTemplate(SheetData.Template.TREBLE);
                    case "Bass Clef" -> data.setTemplate(SheetData.Template.BASS);
                    case "Grand Staff" -> data.setTemplate(SheetData.Template.GRAND);
                }
            }
            EventBus.getInstance().publish(new ChangeProjectPreviewEvent());
        });

        templateForm.add(categoryList, 0, 1);
        templateForm.add(templatesList, 1, 1);

        getChildren().add(templateForm);
    }

    void addSheetPreview() {
        sheetPreview.setWidth(300);
        sheetPreview.setHeight(400);
        GraphicsContext graphicsContext = sheetPreview.getGraphicsContext2D();
        graphicsContext.setFill(Color.WHITESMOKE);
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.fillRect(0, 0, 300, 450);
        getChildren().add(sheetPreview);
    }

    private static Reflection getReflection() {
        Reflection reflection = new Reflection();
        reflection.setFraction(0.7);
        reflection.setTopOffset(0.0);
        reflection.setTopOpacity(0.3);
        reflection.setBottomOpacity(0.0);
        return reflection;
    }

    public void redrawPreview() {
        GraphicsContext graphicsContext = sheetPreview.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, sheetPreview.getWidth(), sheetPreview.getHeight());
        graphicsContext.setFill(Color.WHITESMOKE);
        graphicsContext.fillRect(0, 0, 300, 450);
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        if (!data.getTitle().isEmpty()) {
            graphicsContext.setFont(Font.font("Arial Black", 16));
            graphicsContext.fillText(data.getTitle(), sheetPreview.getWidth() / 2, 60);
        }
        if (!data.getSubtitle().isEmpty()) {
            graphicsContext.setFont(Font.font("Roboto Light", 12));
            graphicsContext.fillText(data.getSubtitle(), sheetPreview.getWidth() / 2, 80);
        }
        if (!data.getComposer().isEmpty()) {
            graphicsContext.setTextAlign(TextAlignment.RIGHT);
            graphicsContext.setFont(Font.font("Times New Roman", 14));
            graphicsContext.fillText(data.getComposer(), sheetPreview.getWidth() - 24, 100);
        }
        if (!(data.getTemplate() == null)) {
            Image image;
            switch (data.getTemplate()) {
                case TREBLE -> image = new Image(Objects.requireNonNull(getClass().getResource("/com/chordly/desktop/asset/img/treble.jpeg")).toExternalForm());
                case BASS -> image = new Image(Objects.requireNonNull(getClass().getResource("/com/chordly/desktop/asset/img/bass.jpeg")).toExternalForm());
                case GRAND -> image = new Image(Objects.requireNonNull(getClass().getResource("/com/chordly/desktop/asset/img/grand.jpeg")).toExternalForm());
                case null, default -> throw new RuntimeException("Error with template");
            }
            graphicsContext.drawImage(image, 10, 120, sheetPreview.getWidth() - 30, sheetPreview.getHeight() / 2);
        }
    }

    @Override
    public void applyEvents() {
        titleInput.setOnKeyTyped(keyEvent -> {
            data.setTitle(titleInput.getText());
            EventBus.getInstance().publish((new ChangeProjectPreviewEvent()));
        });
        subtitleInput.setOnKeyTyped(keyEvent -> {
            data.setSubtitle(subtitleInput.getText());
            EventBus.getInstance().publish(new ChangeProjectPreviewEvent());
        });
        composerInput.setOnKeyTyped(keyEvent -> {
            data.setComposer(composerInput.getText());
            EventBus.getInstance().publish(new ChangeProjectPreviewEvent());
        });
    }

    @Override
    public void applyListeners() {
        EventBus.getInstance().subscribe(ChangeProjectPreviewEvent.class, event -> redrawPreview());
    }

    @Override
    public void applyLayout() {

    }

    @Override
    public void applyStyle(Node target, String className) {

    }
}
