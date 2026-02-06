package com.chordly.desktop.view;

import com.chordly.desktop.model.ui.UIElement;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

import java.util.Objects;


public class HomeView extends ScrollPane implements UIElement {
    private final VBox contentContainer = new VBox();
    private final Label header = new Label("Welcome to Chordly");
    private final AnchorPane heroContainer = new AnchorPane();
    private final VBox encourageContainer = new VBox();

    public HomeView() {
        getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/chordly/desktop/style/home.css")).toExternalForm());
        setContent(contentContainer);
        contentContainer.setMinHeight(1000);
        setupHero();
        setupEncourage();
        setFitToWidth(true);
        setFitToHeight(true);
        setPannable(true);
        setVbarPolicy(ScrollBarPolicy.ALWAYS);
        applyStyle(this, "home-panel");
        applyStyle(contentContainer, "home-content");
        addElements();
    }

    private void addElements() {
        header.getStyleClass().add("home-header");
        contentContainer.getChildren().add(header);
        contentContainer.getChildren().add(heroContainer);
        contentContainer.getChildren().add(encourageContainer);
    }

    private void setupHero() {
        Image heroImg = new Image(Objects.requireNonNull(getClass().getResource("/com/chordly/desktop/asset/img/hero.jpg")).toExternalForm());
        Rectangle heroImgContainer = new Rectangle();
        heroImgContainer.setArcWidth(30);
        heroImgContainer.setArcHeight(20);
        heroImgContainer.setWidth(555);
        heroImgContainer.setHeight(320);
        heroImgContainer.setFill(new ImagePattern(heroImg));
        StackPane imgContainer = new StackPane();
        imgContainer.getChildren().add(heroImgContainer);
        DropShadow shadow = new DropShadow(8, Color.gray(0.5));
        heroImgContainer.setEffect(shadow);

        Label heroHeader = new Label("Compose without limits");
        Label heroParagraph = new Label("Lorem ipsum dolor sit " +
                "amet, consectetur " +
                "adipiscing elit. Nam a purus ornare, interdum " +
                "tellus sed, elementum massa." +
                " Aliquam tristique enim quis feugiat tincidunt. ."
        );
        heroParagraph.setWrapText(true);
        VBox heroVbox = new VBox(heroHeader, heroParagraph);
        applyStyle(heroVbox, "hero-box");
        applyStyle(heroHeader, "hero-header");
        applyStyle(heroParagraph, "hero-paragraph");

        AnchorPane.setLeftAnchor(imgContainer, 30.0);
        AnchorPane.setRightAnchor(heroVbox, 20.0);

        heroContainer.setPadding(new Insets(50, 10, 50, 10));
        heroContainer.getChildren().add(imgContainer);
        heroContainer.getChildren().add(heroVbox);
    }

    private void setupEncourage() {
        Label encourageHeader = new Label("Aliquam tristique enim guis feugiat tincidunt");
        applyStyle(encourageHeader, "hero-header");

        encourageContainer.getChildren().add(encourageHeader);
    }

    @Override
    public void applyEvents() {

    }

    @Override
    public void applyListeners() {

    }

    @Override
    public void applyLayout() {

    }

    @Override
    public void applyStyle(Node target, String className) {
        target.getStyleClass().add(className);
    }
}
